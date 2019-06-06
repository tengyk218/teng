package com.ncme.webapi.controller;

import com.ncme.webapi.domain.User;
import com.ncme.webapi.handler.MyHealthIndicator;
import com.ncme.webapi.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/test")
public class HelloController {

	@Resource
	private UserService userService;

	@Resource
	private MyHealthIndicator myHealthIndicator;

	@RequestMapping("/queryUser")
	@ResponseBody
	public User queryUser(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(StringUtils.isEmpty(id))
		{
			throw new Exception("userid is null");
		}
		//Thread.sleep(50000);
		User result = userService.user(id);
		return result;
	}


	@RequestMapping(value = "/shutDown")
	public void shutDown(String flag){
		myHealthIndicator.setHealth(Boolean.valueOf(flag));
	}
}
