package com.oldboy.ssm.web.controller;

import com.oldboy.ssm.domain.User;
import com.oldboy.ssm.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 *
 */
@Controller
@RequestMapping("/home")
public class HomeController {

	//注入userService
	@Resource(name="userService")
	private UserService us ;

	@RequestMapping("/index")
	public String index(){
		User user = us.findById(5) ;
		System.out.println(user.getName());
		System.out.println("hello");
		return "index" ;
	}
}
