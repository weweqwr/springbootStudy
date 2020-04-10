package com.longlongago.service.impl;

import com.longlongago.entity.User;
import com.longlongago.mapper.UserMapper;
import com.longlongago.service.UserService;
import com.longlongago.utils.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    //首页个人信息展示
    @Override
    public Map<String, Object> selectUserInfo(String account) {
        List<User> list=userMapper.selectUserInfo(account);
        Map<String,Object> map=new HashMap<>();
        map.put("userInfo",list);
        return map;
    }

    @Override
    public User user(String account) {
        User user=userMapper.user(account);
        return user;
    }

    //登录
    @Override
    public Map<String,Object> doLogin(String account, String password, HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        //1、得到主体
        Subject subject= SecurityUtils.getSubject();
        //2、封装用户名和密码
        UsernamePasswordToken token=new UsernamePasswordToken(account,password);
        try {
            subject.login(token);
            ActiveUser activeUser= (ActiveUser) subject.getPrincipal();
            session.setAttribute("ActiveUser",activeUser);
            map.put("token",token);
            map.put("flag",1);
            return  map;
        }catch (AuthenticationException ae){
            map.put("flag",0);
            System.out.println("用户名密码不正确");
        }catch (Exception e){
            map.put("flag",2);
            e.printStackTrace();
        }
        return map;
    }
    //注册
    @Override
    public Map<String,Object> doRegister(String account, String password) {
        int flag;
        try {
//            //账号为盐，迭代两次

            Md5Hash md5=new Md5Hash(password,account,2);
            String repassword=md5.toString();
            System.out.println(repassword);
            userMapper.doRegister(account,repassword);
            flag=1;
        }catch (Exception e) {
           if(e.getCause() instanceof SQLIntegrityConstraintViolationException){
                flag=2;
           }else {
                flag = 0;
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("flag",flag);
        return map;
    }

    @Override
    public Map<String, Object> loginOut() {
        Map<String,Object> map=new HashMap<>();
        //1、得到主体
        Subject subject= SecurityUtils.getSubject();
        try{
            subject.logout();
            map.put("msg","退出成功");
            map.put("flag",1);
        }catch (Exception e){
            map.put("msg","退出失败");
            map.put("flag",0);
            e.printStackTrace();
        }
        return map;
    }
}
