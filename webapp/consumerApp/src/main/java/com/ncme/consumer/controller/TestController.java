package com.ncme.consumer.controller;

import com.ncme.consumer.domain.User;
import com.ncme.consumer.service.TestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope //开启更新功能
@RequestMapping("/")
public class TestController {

	@Resource
    private TestService testService;

	@Value("${custName}")
	private String custName;

	@RequestMapping(value="/queryUser")
	public void queryUser(String id) throws Exception{
		System.out.println(custName);
		User user = new User();
		user.setId(id);
		user.setUsername("周楠");
		user.setSex("男");
		User result = new User();
		try {
			result = testService.queryUser(id);
		}catch (Exception ex){
			throw new Exception("自定义异常：" +ex.getMessage());
		}
		System.out.println("callback：" + result.getUsername()+", " + result.getPassword());
	}

	@RequestMapping(value="/saveUser")
	public void saveUser() throws Exception{
		User user = new User();
		user.setUsername("周楠");
		user.setSex("男");
		try {
			testService.saveUser(user);
		}catch (Exception ex){
			throw new Exception("自定义异常：" +ex.getMessage());
		}
	}
}
