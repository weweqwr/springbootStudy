package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_admin;
import com.fontgoaway.entity.Gw_permission;
import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.serve.PermissionServe;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.acl.Permission;
import java.util.List;
import java.util.Map;

@RestController
public class PermissionController {
    @Autowired
    PermissionServe permissionServe;
    //删除角色的权限
    @RequiresPermissions("Role")
    @RequestMapping(value = "deleteRolePermissionByRoleId",method = RequestMethod.POST)
    public Map<String,Object> deleteRolePermissionByRoleId(@RequestBody Gw_permission permission) {
        int roleId=permission.getRoleId();
        int perId=permission.getPerId();
        Map<String,Object> map=permissionServe.deleteRolePermissionByRoleId(roleId,perId);
        return map;
    }
    //添加权限
    @RequiresPermissions("Role")
    @RequestMapping(value = "addRolePermission",method = RequestMethod.POST)
    public Map<String,Object> addRolePermission(@RequestBody Gw_permission permission) {
        int roleId=permission.getRoleId();
        int perId=permission.getPerId();
        Map<String,Object> map=permissionServe.addRolePermission(roleId,perId);
        return map;
    }
    //展示导航栏
    @RequestMapping(value = "queryPermissionNav",method = RequestMethod.POST)
    public Map<String,Object> queryPermissionNav(@RequestBody Gw_permission permission) {
        String account=permission.getAccount();
        Map<String,Object> map=permissionServe.queryPermissionNav(account);
        return map;
    }


}
