package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.BusinessException;

@RestController
public class HelloWod {
	// 自定义属性
	@Value("${agan.msg}")
	private String msg;

	@RequestMapping("/hello")
	public String hello(){
		 int no = 1 / 0;
		throw new BusinessException("100", "用户名密码错误！");
		// return this.msg;
	}

}
