package com.ybjt.home.HomeController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	
	/**
	 * $跳转Echarts
	 */
	@RequestMapping("/loadEcharts")
    public String loadEcharts(){
        return "home/loadEcharts";
    }
	
	/**
	 * $跳转Echarts-gl;
	 */
	@RequestMapping("/loadEchartsGL")
    public String loadEchartsGL(){
        return "home/loadEchartsGL";
    }
	
	
	/**
	 * $跳转kaptcha_code;
	 */
	@RequestMapping("/kaptcha_code")
    public String kaptcha_code(){
        return "home/kaptcha_code";
    }
	
	/**
	 * $跳转openlayers_page;
	 */
	@RequestMapping("/openlayers_page")
    public String openlayers_page(){
        return "home/openlayers_page";
    }
	
}
