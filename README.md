# springBoot_web

```java
TALK IS CHEAP SHOW ME THE CODE!
```



springboot开发web项目，主流功能的集成。<br>

2019.07.18  项目创建，访问测试成功。上传至github!<br>

ztree的集成、ajax的使用、freemarker的使用，springboot热部署，layui前端模板的使用！<br>

springboot启动图形的配置、自定义服务端口的配置！！<br>

阿里巴巴fastjson的使用、数据库连接池druid的使用！！<br>

使用layui的layer组件，无需单独引用！！

```javascript
//项目只需引入layui即可使用layer组件
layui.use('layer', function(){
  var layer = layui.layer;
});              
```

<br>

mybatis传入对象更新数据

```java
//mapper接口
public void saveInfo(Location location);
```

```xml
<!-- 查询全部 (动态sql非空判断)-->
<update id="saveInfo" parameterType="location">
   update sys_location set LOCATION_EDITTIME =#{LOCATION_EDITTIME}, LOCATION_NAME = #{LOCATION_NAME},LOCATION_DESC = #{LOCATION_DESC} where LOCATION_ID = #{LOCATION_ID};
</update>
```

<br>

mysql数据库中的字段为datetime,对应实体的字段类型应该为java.util.Date，需要更新时间是，直接

```java
//import java.util.Date;
location.setLOCATION_EDITTIME(new Date());
```

<br>

ajax异步请求页面，利用layer组件弹框显示

```java
//触发函数的时候，异步请求后台页面，layer弹框显示！	
function toAddTree(){
    $.ajax({
        type:'POST',
        url:'/location/addRootLocation',
        data:{},
        dataType:"html",
        success:function(data){
            //iframe层
            layer.open({
                title:'添加根节点',
                fix:false,
                maxmin: true,
                offset: 'auto',
                scrollbar: true,
                type: 1,
                skin: 'layui-layer-molv', //皮肤即颜色
                area: ['420px', '240px'], //宽高
                content: data
            });
        },
        error:function(){
            layer.msg("未知错误");
        }
    });
};
```

```javascript
//ajax异步提交数据，success触发时，重新加载tree结构，利用js的定时器，1秒后弹层清除！！！
$.ajax({
    type:"POST",
    url:'/location/addRoot',
    data:{"name":location_name,
          "desc":location_desc		
         },
    success:function(data){
        layer.msg(data);
        treeutil.treeinit("#treemenu", setting);
        var time = setTimeout(function(){
            layer.closeAll();
        },1000);
        cleansetTimeout(time);
    },
    error:function(){
        layer.msg("添加错误！")
    }
});
```

```xml
<!--mybatis传入对象，插入新数据！-->
<insert id="addRoot" parameterType="location">
    	insert into sys_location values (#{LOCATION_ID},#{LOCATION_NAME},#{LOCATION_CREAETIME},#{LOCATION_EDITTIME},#{LOCATION_PID},#{LOCATION_DESC});
</insert>
```

菜单三级联动完成

```javascript
//监听layui select改变 
form.on('select(city)', function(data){
		  $("#xian").empty();
		 var pid = data.value;
    //异步获取数据
		 $.ajax({
			 type:"POST",
			 url:"/location/twoLocation",
			 data:{"pid":pid},
			 dataType:"json",
			 success:function(data){
                 //返回数组，遍历添加到页面
				for(var i = 0;i<data.length;i++){
					$("#xian").append("<option value='"+data[i].lOCATION_ID+"'>"+data[i].lOCATION_NAME+"</option>");
				}
                 //form表单重新渲染，否则有数据但是不显示！
				  form.render();
			 },
			 error:function(){
				 console.log("读取数据失败!");
			 }
		 	});
		});

```

```java
	/**
	 * 根据pid查询子节点
	 */
	@RequestMapping("/twoLocation")
	@ResponseBody
	public String twoLocation(String pid) {
		List<Location> list = locationService.twoLocation(pid);
        //fastjson 数据转换并返回前端
		String json = JSON.toJSONString(list);
		return json;
	}

```

js完成实时时间的显示、layui进度条的使用！