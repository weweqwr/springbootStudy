package com.longer.demo.comtroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longer.demo.pojo.User;
import com.longer.demo.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/findAll")
	@ResponseBody // 结果返回json
	public List<User> findAll() {
		List<User> list = userService.findAll();
		return list;
	}

}
