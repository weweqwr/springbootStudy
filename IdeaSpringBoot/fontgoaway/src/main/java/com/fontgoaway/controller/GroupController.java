package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_group;
import com.fontgoaway.entity.Gw_permission;
import com.fontgoaway.serve.GroupServe;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GroupController {
    @Autowired
    GroupServe groupServe;
    //查询群的信息
    @RequiresPermissions("group")
    @RequestMapping(value = "queryGroup",method = RequestMethod.POST)
    public Map<String,Object> queryGroup(@RequestBody Gw_group group) {
        Map<String,Object> map=groupServe.queryGroup(group.getCurPage(),group.getId());
        return map;
    }
    //屏蔽群
    @RequiresPermissions("group")
    @RequestMapping(value = "shieldGroup",method = RequestMethod.POST)
    public Map<String,Object> shieldGroup(@RequestBody Gw_group group) {
        Map<String,Object> map=groupServe.shieldGroup(group.getId(),group.getState());
        return map;
    }

}
