package com.ybjt.home.HomeController;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ybjt.location.bean.Location;
import com.ybjt.location.service.LocationService;

import core.tools.WindowsInfoUtil;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private LocationService locationService;
	/**
	 * $跳转图片轮播页
	 */
	@RequestMapping("/lunbo")
    public String execute(Map map){
		List<Location> list = locationService.selectRootLocation();
		map.put("list", list);
        return "home/lunbo";
    }
	
	/**
	 * $跳转ztree树形菜单location管理
	 */
	@RequestMapping("/ztreeLocation")
    public String ztreeLocation(){
        return "home/ztreeLocation";
    }
	
	/**
	 * $跳转系统信息，利用工具类读取系统信息
	 */
	@RequestMapping("/computerInfo")
    public String computerInfo(Map map){
		String cpu = WindowsInfoUtil.getCpuRatioForWindows();
		String rom = WindowsInfoUtil.getMemery();
		String ram = WindowsInfoUtil.getDisk();
		map.put("cpu", cpu);
		map.put("rom", rom);
		map.put("ram", ram);
        return "home/computerInfo";
    }
	
	
	
	
}