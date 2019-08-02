package com.ybjt.login.LoginController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * #登录控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/tologin")
	public String tologin(){
		return "/login/tologin";
	}
}
