package com.longlongago.controller;

import com.longlongago.entity.User;
import com.longlongago.service.UserService;
import com.longlongago.utils.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BasicController {
    @Autowired
    UserService userService;


    //返回首页的信息
    @RequestMapping(value="index",method =RequestMethod.POST)
       public Map<String, Object> index(HttpSession session) {
        ActiveUser activeUser= (ActiveUser) session.getAttribute("ActiveUser");
        User user= activeUser.getUser();
        String account=user.getAccount();
        Map<String, Object> map = new HashMap<>();
        map=userService.selectUserInfo(account);
        return map;
    }

    //登录
    @RequestMapping(value="doLogin",method =RequestMethod.POST)
    public Map<String, Object> doLogin(@RequestBody Map map1,HttpSession session) {
        String account=map1.get("account").toString();
        String password=map1.get("password").toString();
        Map<String, Object> map = new HashMap<>();
        map=userService.doLogin(account,password,session);
        return map;
    }
    //注册
    @RequestMapping(value="doRegister",method =RequestMethod.POST)
    public Map<String, Object> doRegister(@RequestBody Map map1) {
        String account=map1.get("account").toString();
        String password=map1.get("password").toString();
        Map<String, Object> map = new HashMap<>();
        map=userService.doRegister(account,password);
        return map;
    }
    //判断session是否有信息
    @RequestMapping(value="loginFlag",method =RequestMethod.POST)
    public Map<String, Object> loginFlag(HttpSession session) {
        //1、得到主体
        Subject subject= SecurityUtils.getSubject();
        Map<String, Object> map = new HashMap<>();
        if(subject.isAuthenticated()){
            ActiveUser activeUser= (ActiveUser) session.getAttribute("ActiveUser");
            User user= activeUser.getUser();
            String account=user.getAccount();
            map.put("msg","已登录");
            map.put("flag",1);
            map.put("username",account);
        }else {
            map.put("msg","未登录");
            map.put("flag",0);
        }
        return map;
    }
    //退出登录
    @RequestMapping(value="loginOut",method =RequestMethod.POST)
    public Map<String, Object> loginOut() {
        Map<String, Object> map=new HashMap<>();
        map=userService.loginOut();
        return map;
    }
}
