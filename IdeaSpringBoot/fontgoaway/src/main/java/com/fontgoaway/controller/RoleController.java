package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.entity.Gw_rotation;
import com.fontgoaway.serve.RoleServe;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    RoleServe roleServe;
    //查询全部角色
    @RequiresPermissions("Role")
    @RequestMapping(value = "queryAllRole",method = RequestMethod.POST)
    public Map<String,Object> queryAllRole(@RequestBody Gw_role role) {
        int curPage=role.getCurPage();
        int roleId=0;
        if(role.getRoleId()!=0) {
            roleId = role.getRoleId();
        }
        Map<String,Object> map=roleServe.queryAllRole(curPage,roleId);
        return map;
    }
    //处理角色状态
    @RequiresPermissions("Role")
    @RequestMapping(value = "handleRoleState",method = RequestMethod.POST)
    public Map<String,Object> handleRoleState(@RequestBody Gw_role role) {
        int roleId=role.getRoleId();
        int state=role.getState();
        Map<String,Object> map=roleServe.handleRoleState(roleId,state);
        return map;
    }
    //查找该角色没有的权限
    @RequiresPermissions("Role")
    @RequestMapping(value = "queryRoleNoPermission",method = RequestMethod.POST)
    public Map<String,Object> queryRoleNoPermission(@RequestBody Gw_role role) {
        int roleId=role.getRoleId();
        Map<String,Object> map=roleServe.queryRoleNoPermission(roleId);
        return map;
    }
    //添加角色
    @RequiresPermissions("Role")
    @RequestMapping(value = "addRole",method = RequestMethod.POST)
    public Map<String,Object> addRole(@RequestBody Gw_role role) {
        String rolename=role.getRolename();
        Map<String,Object> map=roleServe.addRole(rolename);
        return map;
    }
}
