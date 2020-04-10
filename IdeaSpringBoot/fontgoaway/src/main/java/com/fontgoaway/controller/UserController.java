package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_group;
import com.fontgoaway.entity.Gw_user;
import com.fontgoaway.serve.GroupServe;
import com.fontgoaway.serve.UserServe;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserServe userServe;
    //查询用户的信息
    @RequiresPermissions("user")
    @RequestMapping(value = "queryUser",method = RequestMethod.POST)
    public Map<String,Object> queryGroup(@RequestBody Gw_user user) {
        Map<String,Object> map=userServe.queryUser(user.getCurPage(),user.getId());
        return map;
    }
    //屏蔽用户
    @RequiresPermissions("user")
    @RequestMapping(value = "shieldUser",method = RequestMethod.POST)
    public Map<String,Object> shieldGroup(@RequestBody Gw_user user) {
        Map<String,Object> map=userServe.sheildUser(user.getId(),user.getState());
        return map;
    }

}
