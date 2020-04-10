package com.longer.demo.controller;

import com.longer.demo.pojo.User;
import com.longer.demo.service.UserService;
import com.longer.demo.utils.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.origin.SystemEnvironmentOrigin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("login")
public class LogimController {
    @Autowired
    UserService userService;

    @RequestMapping("success")
    @ResponseBody
    public Map<String, Object> success(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        User user = (User) session.getAttribute("user");
        map.put("a", user);
        return map;
    }

    @RequestMapping("login.do")
    @ResponseBody
    public Map<String, Object> login(String username, String password, Integer rememberMe, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //1、得到主题
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        System.out.println(username + "aaaaa");
        try {
            if (rememberMe != null && rememberMe == 1) {
                // 说明记住我
                token.setRememberMe(true);
            }
            subject.login(token);
            ActiveUser activerUser = (ActiveUser) subject.getPrincipal();
            session.setAttribute("user", activerUser.getUser());
            map.put("code", 0);
            map.put("msg", "认证通过");
            return map;
        } catch (AuthenticationException e) {
            System.out.println("用户名或者密码不正确");
        }
        map.put("code", -1);
        map.put("msg", "认证失败");
        return map;

    }

}
