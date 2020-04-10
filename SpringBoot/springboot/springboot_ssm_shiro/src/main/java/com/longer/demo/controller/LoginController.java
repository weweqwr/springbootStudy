package com.longer.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longer.demo.pojo.User;
import com.longer.demo.service.UserService;
import com.longer.demo.utils.ActiveUser;

@Controller // 用ResController重定向会失败，返回字符串
@RequestMapping("login")
public class LoginController {
	@Autowired
	UserService userService;

	@RequestMapping("success")
	@ResponseBody // 要注入这个，不然找不到模板页
	public Map<String, Object> success(HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		User user = (User) session.getAttribute("user");
		map.put("a", user);
		return map;
	}

	@RequestMapping("success1")
	@ResponseBody // 要注入这个，不然找不到模板页
	public Object success1(HttpSession session) {
		User user = (User) session.getAttribute("user");

		return user;
	}

	/**
	 * 完成登陆的方法
	 */
	@RequestMapping("login.do")
	public String login(String username, String password, Integer rememberMe, HttpSession session) {

		// 1，得到主体
		Subject subject = SecurityUtils.getSubject();
		// 2,封装用户名和密码
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			if (rememberMe != null && rememberMe == 1) {
				// 说明记住我
				token.setRememberMe(true);
			}

			subject.login(token);
			ActiveUser activerUser = (ActiveUser) subject.getPrincipal();
			session.setAttribute("user", activerUser.getUser());
			return "redirect:/success.html";

		} catch (AuthenticationException e) {
			System.out.println("用户名或密码不正确");
		}
		return "redirect:/login.html";
	}

}
