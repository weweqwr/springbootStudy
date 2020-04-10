package com.longer.demo.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {
    @RequiresPermissions({"person:query"})
    @RequestMapping("queryUser")
    public Map<String, Object> queryUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "查询成功");
        return map;
    }

    @RequiresPermissions({"person:add"})
    @RequestMapping("addUser")
    public Map<String, Object> addUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "查询成功");
        return map;
    }

    @RequiresPermissions({"person:update"})
    @RequestMapping("updateUser")
    public Map<String, Object> updateUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "查询成功");
        return map;
    }

    @RequiresPermissions({"person:delete"})
    @RequestMapping("deleteUser")
    public Map<String, Object> deleteUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "查询成功");
        return map;
    }

    @RequiresPermissions({"person:export"})
    @RequestMapping("exportUser")
    public Map<String, Object> exportUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "查询成功");
        return map;
    }

}
