package com.ybjt.consumer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ConsumerController {
	
	/**
	 * springboot启动访问测试，
	 * http://localhost:8080/hello 返回：Hello World!
	 * localhost:8080不会访问tomcat的页面。
	 * @return
	 */
	@RequestMapping("/hello")
	@ResponseBody
	 public String hello() {
		return "Hello World!543534524";
	}
}
