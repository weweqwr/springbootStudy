package com.goaway.controller;

import com.goaway.serve.UserServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserServe userServe;

    //插入用户信息
    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    public Map<String, Object> createUser(String avatarUrl, String openid, String country, String province, String city, Short gender
            , String nickname) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(avatarUrl);
        map = userServe.insertUser(avatarUrl, openid, country, province, city, gender, nickname);
        return map;
    }

    //根据openid查询用户信息
    @RequestMapping(value = "queryUserByOpenId", method = RequestMethod.POST)
    public Map<String, Object> queryUserByOpenId(String openid) {
        Map<String, Object> map = new HashMap<>();
        map = userServe.queryUserByOpenId(openid);
        return map;
    }
    //查询未签到的人
    @RequestMapping(value = "queryNoReceived",method=RequestMethod.POST )
    public Map<String, Object> queryNoReceived(int noticeId) {
        Map<String, Object> map = new HashMap<>();
        map=userServe.queryNoReceived(noticeId);
        return map;
    }

}
