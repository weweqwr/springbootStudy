package com.yuntu.controller;

import com.yuntu.entity.Permission;
import com.yuntu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    PermissionService permissionService;

    @RequestMapping("login")
    @ResponseBody
    public List<String> login(Integer perid) {
        Map<String, Object> map = new HashMap<>();
        List<String> list = permissionService.query(1);
        for (int i = 0; i < list.size(); i++) {
            map.put("a" + i, list.get(i));
        }
        return list;
    }
}
