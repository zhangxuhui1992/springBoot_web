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

Echarts的简单上手使用。简单图表！支持异步加载数据！

~~~java
myChart.showLoading();//加载动画。
myChart.hideLoading();//加载完成自动取消
// 异步加载数据,ajax异步获取数据前使用加载动画，success函数触发时取消加载动画，
//并且将获取的数据，加载进图表！myChart.setOption({});
$.get('data.json').done(function (data) {
    // 填入数据
    myChart.setOption({
        xAxis: {
            data: data.categories
        },
        series: [{
            // 根据名字对应到相应的系列
            name: '销量',
            data: data.data
        }]
    });
~~~



~~~javascript
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init($("#main")[0]);
var option = {
	    title: {
	        text: 'ECharts 入门示例'
	    },
	    tooltip: {},
	    legend: {
	        data:['销量']
	    },
	    xAxis: {
	        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
	    },
	    yAxis: {},
	    series: [{
	        name: '销量',
	        type: 'bar',
	        data: [5, 20, 36, 10, 10, 20]
	    }]
	};
// 使用刚指定的配置项和数据显示图表。
	myChart.setOption(option);
~~~

# openlayers集成开发

~~~html
<!-- 引入相关资源-->
<script type="text/javascript" src="js/ol.js"></script>
<link rel="stylesheet" href="css/ol.css">
~~~

~~~html
<!-- 打开就能用的资源-->
<div id='map' style='height: 500px;border: 1px solid red;'></div>
<button id='big'>放大</button>
<button id='small'>缩小</button>
<button id='fly-to-bern'>定位到北京</button>
<button id='drawPoint'>画点</button>
<button id='drawLine'>画线</button>
<button id='drawCircle'>画圆</button>
<button id='drawPolygon'>画多边形/区域</button>
<button id='cleanMap'>清空图层</button>
<script>
	//创建地图
	var map = new ol.Map({
		controls: ol.control.defaults({
	          attributionOptions: ({
	            collapsible: false
	          })
	        }).extend([
	            new ol.control.MousePosition(),
	        ]),
	      // 设置地图图层
	      layers: [
	        // 创建一个使用Open Street Map地图源的瓦片图层
	        new ol.layer.Tile({source: new ol.source.OSM()})
	      ],
	      // 设置显示地图的视图
	      view: new ol.View({
	        center: [0, 0],    // 定义地图显示中心于经度0度，纬度0度处
	        zoom: 5,// 并且定义地图显示层级为5
	     	// 指定投影使用EPSG:4326
	        projection: 'EPSG:4326',
	      }),
	      // 让id为map的div作为地图的容器
	      target: 'map',
	      //
	      loadTilesWhileAnimating: true,
	  });
	
	//放大
	$("#big").click(function(){
		var view = map.getView();
		var zoom = view.getZoom();
		view.setZoom(zoom+1);
	});
	
	//缩小
	$("#small").click(function(){
		var view = map.getView();
		var zoom = view.getZoom();
		view.setZoom(zoom-1);
	});
	
	//动画定位到某点
	$("#fly-to-bern").click(function(){
		var view = map.getView();
		var beijing = ol.proj.fromLonLat([116.40, 39.90]);
		view.animate({
          center: beijing,
          duration: 3000
        });
	});
	
	 // 添加一个绘制的线使用的layer
    var vectorLayer = new ol.layer.Vector({
        source: new ol.source.Vector()
    });
    map.addLayer(vectorLayer);
    var draw;
    
 	/* // 监听singleclick事件
    map.on('singleclick', function(event){
       console.log(event.coordinate);
    }); */
 	
    //画点
	$("#drawPoint").click(function(){
		map.removeInteraction(draw);
       // 添加一个绘制的线使用的layer
        var style = new ol.style.Style({
                  image: new ol.style.Icon({
                	src:'images/favicon.ico'
                  }),
                  text:new ol.style.Text({
                	  text:'我是一个点',
                	  font:'18px sans-serif ',
                	  offsetY:'-20',
                	  fill:new ol.style.Fill({
                		  color:'red'
                	  })
                  })
              });
        vectorLayer.setStyle(style);
        draw = new ol.interaction.Draw({
        	  type:'Point',
        	  source:vectorLayer.getSource()
        	});
       map.addInteraction(draw);
       //绘制完毕后，获取绘制的坐标，点坐标为数组
       draw.on('drawend',function(event){
    	   var geom = event.feature.getGeometry();
    	   var position = geom.getCoordinates();
    	   console.log(position);
    	});
     //清空添加的矢量图层
		vectorLayer.getSource().clear();
	});
	
	 //画线
	$("#drawLine").click(function(){
		map.removeInteraction(draw);
		var style = new ol.style.Style({
			 stroke:new ol.style.Stroke({
				color:'red',
				width:'10px'
			 }),
			 text:new ol.style.Text({
				 text:'我是一条线',
				 font:'normal 18px 微软雅黑',
				 textAlign:'center',
				 textBaseline:'middle',
				 fill:new ol.style.Fill({
					 color:'#aa3300'
				 })
			 }),
			 zIndex:20
		 });
		vectorLayer.setStyle(style);
		draw = new ol.interaction.Draw({
	      	  type:'LineString',
	      	  source:vectorLayer.getSource()
    	  });
		map.addInteraction(draw);
		//线的坐标为二维数组
		draw.on('drawend',function(event){
	    	   var geom = event.feature.getGeometry();
	    	   var position = geom.getCoordinates();
	    	   console.log(position);
	    	});
		//清空添加的矢量图层
		vectorLayer.getSource().clear();
	});
	 
	 //画圆
	 $("#drawCircle").click(function(){
		 map.removeInteraction(draw);
		 var style = new ol.style.Style({
			 stroke:new ol.style.Stroke({
				color:'#ffcc33',
				width:'2'
			 }),
			 text:new ol.style.Text({
				 text:'我是圆',
				 font:'normal 18px 微软雅黑',
				 textAlign:'center',
				 textBaseline:'middle',
				 fill:new ol.style.Fill({
					 color:'rgba(255,33,0,1)'
				 })
			 }),
			 fill:new ol.style.Fill({
				 color:'rgba(0,198,255,0.4)'
			 })
		 });
		vectorLayer.setStyle(style);
		 draw = new ol.interaction.Draw({
	      	  type:'Circle',
	      	  source:vectorLayer.getSource()
   	  	});
		map.addInteraction(draw);
		//圆的圆心坐标和半径
		draw.on('drawend',function(event){
	    	   var geom = event.feature.getGeometry();
	    	   var position = "中心点"+geom.getCenter()+"半径"+geom.getRadius();
	    	   console.log(position);
	    	});
		//清空添加的矢量图层
		vectorLayer.getSource().clear();
	 });
	
	 
	 //画多边形
	 $("#drawPolygon").click(function(){
		 map.removeInteraction(draw);
		 var style = new ol.style.Style({
			 stroke:new ol.style.Stroke({
				color:'#ffcc33',
				width:'2'
			 }),
			 text:new ol.style.Text({
				 text:'我是多边形',
				 font:'normal 18px 微软雅黑',
				 textAlign:'center',
				 textBaseline:'middle',
				 fill:new ol.style.Fill({
					 color:'rgba(255,33,0,1)'
				 })
			 }),
			 fill:new ol.style.Fill({
				 color:'rgba(0,198,255,0.4)'
			 })
		 });
		vectorLayer.setStyle(style);
		 draw = new ol.interaction.Draw({
	      	  type:'Polygon',
	      	  source:vectorLayer.getSource()
   	  	});
		map.addInteraction(draw);
		//多边形坐标是二维数组
		draw.on('drawend',function(event){
				var geom = event.feature.getGeometry();
	    	   	var position = geom.getCoordinates();
	    	   	console.log(position);
	    	});
		//清空添加的矢量图层
		vectorLayer.getSource().clear();
	 });
	 
	 //清空图层
	 $("#cleanMap").click(function(){
		vectorLayer.getSource().clear();
	});
    
    //初始化点
	 $("#addPoint").click(function(){
		 var pointFeature = new ol.Feature({
			 geometry:new ol.geom.Point([116.40, 39.90]),
			 name:"1324",
			 id:"666"
		 });
		 var style = new ol.style.Style({
             image: new ol.style.Icon({
           	 src:'images/favicon.ico'
             	}),
             text:new ol.style.Text({
           	  text:'我是一个点',
           	  font:'18px sans-serif ',
           	  offsetY:'-20',
           	  fill:new ol.style.Fill({
           		  color:'red'
           	  })
             })
         });
		 pointFeature.setStyle(style);
		 vectorLayer.getSource().addFeature(pointFeature);
	 });
	 
	 //初始化线
	 $("#addLine").click(function(){
		 var linefeature = new ol.Feature({
		     	geometry:new ol.geom.LineString([[113.5991,24.8166], [114.5991,25.8166]])
		    	});
		 var style = new ol.style.Style({
			 stroke:new ol.style.Stroke({
				color:'red',
				width:'10px'
			 }),
			 text:new ol.style.Text({
				 text:'我是一条线',
				 font:'normal 18px 微软雅黑',
				 textAlign:'center',
				 textBaseline:'middle',
				 fill:new ol.style.Fill({
					 color:'#aa3300'
				 })
			 }),
			 zIndex:20
		 });
		 linefeature.setStyle(style);
		 vectorLayer.getSource().addFeature(linefeature);
	 });
	 
	 //初始化多边形区域
	 $("#addPolygon").click(function(){
		 var PolygonFeature = new ol.Feature({
			 geometry:new ol.geom.Polygon([[[110, 39], [116, 39], [116, 33], [110, 33], [110, 39]]])
		 });
		 var style = new ol.style.Style({
			 stroke:new ol.style.Stroke({
				color:'#ffcc33',
				width:'2'
			 }),
			 text:new ol.style.Text({
				 text:'我是多边形',
				 font:'normal 18px 微软雅黑',
				 textAlign:'center',
				 textBaseline:'middle',
				 fill:new ol.style.Fill({
					 color:'rgba(255,33,0,1)'
				 })
			 }),
			 fill:new ol.style.Fill({
				 color:'rgba(0,198,255,0.4)'
			 })
		 });
		 PolygonFeature.setStyle(style);
		 vectorLayer.getSource().addFeature(PolygonFeature);
	 });
</script>

~~~

