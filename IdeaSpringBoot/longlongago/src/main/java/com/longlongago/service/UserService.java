package com.longlongago.service;

import com.longlongago.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    //简介用户首页的个人信息
    Map<String,Object> selectUserInfo(String account);
    //根据account获取用户信息(shiro)
    User user(String account);
    //登录
    Map<String,Object> doLogin(String account, String password, HttpSession session);
    //注册
    Map<String,Object> doRegister(String account, String password);
    //退出
    Map<String, Object> loginOut();

}
