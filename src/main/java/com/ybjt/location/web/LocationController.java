package com.ybjt.location.web;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ybjt.location.bean.Location;
import com.ybjt.location.service.LocationService;
import com.ybjt.tree.dto.BasicTree;
/**
 * city交互的控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/location")
public class LocationController {
	
	@Autowired
	private LocationService locationService;
	private List<Location> twoLocation;
	
	/**
	 * $查询全部
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectAll")
	public String selectAll(String page,String limit) {
		List<Location> selectAll = locationService.selectAlls(page,limit);
		List<Location> selectAll2 = locationService.selectAll();
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		for(int i = 0;i<selectAll.size();i++) {
			JSONObject obj = new JSONObject();
			obj.put("LOCATION_ID", selectAll.get(i).getLOCATION_ID());
			obj.put("LOCATION_NAME", selectAll.get(i).getLOCATION_NAME());
			Date create = selectAll.get(i).getLOCATION_CREAETIME();
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			obj.put("LOCATION_CREAETIME", format.format(create));
			obj.put("LOCATION_EDITTIME", format.format(selectAll.get(i).getLOCATION_EDITTIME()));
			obj.put("LOCATION_PID", selectAll.get(i).getLOCATION_PID());
			obj.put("LOCATION_DESC", selectAll.get(i).getLOCATION_DESC());
			array.add(obj);
		}
		object.put("data", array);
		object.put("code", 0);
		object.put("msg", "");
		object.put("count", selectAll2.size());
		return JSON.toJSONString(object);
	}
	
	/**
	 * $查询树菜单
	 * @return
	 */
	@RequestMapping("/loadTreeData")
	@ResponseBody
	public String loadTreeData() {
		List<BasicTree> list = locationService.loadTreeData();
		JSONArray jsonArray = new JSONArray();
		for(BasicTree tree:list) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", tree.getId());
			jsonObject.put("pId", tree.getPId());
			jsonObject.put("name", tree.getName());
			jsonObject.put("open", tree.getOpen());
			jsonArray.add(jsonObject);
		}
		return JSON.toJSONString(jsonArray, SerializerFeature.DisableCircularReferenceDetect);
	}
	
	/**
	 * $加载location详情列表
	 */
	@RequestMapping("/locationinfo")
	public String locationinfo(String id,Map map) {
		Location bean = locationService.selectById(id);
		map.put("bean", bean);
		return "home/locationinfo";
	}
	
	/**
	 *$保存修改
	 */
	@RequestMapping("/saveInfo")
	@ResponseBody
	public String saveInfo(String id,String name,String desc) {
		if(id != null && !"".equals(id) || name != null && !"".equals(name) || desc != null && !"".equals(desc)) {
			Location location = new Location();
			location.setLOCATION_ID(id);
			location.setLOCATION_NAME(name);
			location.setLOCATION_DESC(desc);
			location.setLOCATION_EDITTIME(new Date());
			locationService.saveInfo(location);
			return "修改成功！";
		}else {
			return "修改失败！";
		}
	}
	
	/**
	 * $跳转添加根节点页面
	 */
	@RequestMapping("/addRootLocation")
	public String addRootLocation() {
		return "add/addTreeNode";
	}
	
	/**
	 * $跳转添加子节点页面
	 */
	@RequestMapping("/addChildLocation")
	public String addChildLocation(String pid,Map map) {
		map.put("pid", pid);
		return "add/addChildTreeNode";
	}
	
	/**
	 * 添加根节点
	 */
	@RequestMapping("addRoot")
	@ResponseBody
	public String addRoot(String name,String desc) {
		Location lo = new Location();
		lo.setLOCATION_NAME(name);
		lo.setLOCATION_DESC(desc);
		locationService.addRoot(lo);
		return "添加成功!";
	}
	
	/**
	 * 添加子节点
	 */
	@RequestMapping("addchild")
	@ResponseBody
	public String addchild(String name,String desc,String pid) {
		Location lo = new Location();
		lo.setLOCATION_NAME(name);
		lo.setLOCATION_DESC(desc);
		lo.setLOCATION_PID(pid);
		locationService.addchild(lo);
		return "添加成功!";
	}
	
	/**
	 * deleteNotes删除节点
	 */
	@RequestMapping("/deleteNotes")
	@ResponseBody
	public String deleteNotes(String id) {
		locationService.deleteNotes(id);
		return "删除成功！";
	}
	
	/**
	 * 根据pid查询子节点
	 */
	@RequestMapping("/twoLocation")
	@ResponseBody
	public String twoLocation(String pid) {
		List<Location> list = locationService.twoLocation(pid);
		String json = JSON.toJSONString(list);
		return json;
	}
}

