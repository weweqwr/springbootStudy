package com.fontgoaway.controller;

import com.fontgoaway.entity.Gw_admin;
import com.fontgoaway.serve.AdminServe;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    AdminServe adminServe;

    //管理员登录
    @RequestMapping(value = "doLogin", method = RequestMethod.POST)
    public Map<String, Object> doLogin(@RequestBody Gw_admin gw_admin, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String account = gw_admin.getAccount();
        String password = gw_admin.getPassword();
        map = adminServe.doLogin(account, password, session);
        return map;
    }

    //超级管理员登录
    @RequestMapping(value = "superLogin", method = RequestMethod.POST)
    public Map<String, Object> superLogin(@RequestBody Gw_admin gw_admin, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        String account = gw_admin.getAccount();
        String password = gw_admin.getPassword();
        map = adminServe.superLogin(account, password, session);
        return map;
    }


    //注册
    @RequestMapping(value = "doRegister", method = RequestMethod.POST)
    public Map<String, Object> doRegister(@RequestBody Gw_admin gw_admin) {
        Map<String, Object> map = new HashMap<>();
        String account = gw_admin.getAccount();
        String password = gw_admin.getPassword();
        map = adminServe.doRegister(account, password);
        return map;
    }

    //查询所有管理员信息
    @RequiresPermissions(value={"apply","handleAdmin"},logical= Logical.OR)
    @RequestMapping(value = "queryAllAdmin", method = RequestMethod.POST)
    public Map<String, Object> queryAllAdmin(@RequestBody Gw_admin admin) {
        Map<String, Object> map = new HashMap<>();
        int verify = admin.getVerify();
        String account = null;
        if (admin.getAccount() != null || admin.getAccount() != "") {
            account = admin.getAccount();
        }
        int curPage = admin.getCurPage();
        map = adminServe.queryAllAdmin(verify, account, curPage);
        return map;
    }

    //处理审核
    @RequiresPermissions("apply")
    @RequestMapping(value = "handleVertity", method = RequestMethod.POST)
    public Map<String, Object> handleVertity(@RequestBody Gw_admin admin) {
        Map<String, Object> map = new HashMap<>();
        int state = admin.getState();
        int id = admin.getId();
        map = adminServe.handleVertity(id, state);
        return map;
    }

    //查询该账号拥有的角色
    @RequiresPermissions("handleAdmin")
    @RequestMapping(value = "queryAdminRole", method = RequestMethod.POST)
    public Map<String, Object> queryAdminRole(@RequestBody Gw_admin admin) {
        Map<String, Object> map = new HashMap<>();
        String account = null;
        if (admin.getAccount() != null) {
            account = admin.getAccount();
        }
        map = adminServe.queryAdminRole(admin.getCurPage(), account);
        return map;
    }

    //给账号添加角色
    @RequiresPermissions("handleAdmin")
    @RequestMapping(value = "addAdminRole", method = RequestMethod.POST)
    public Map<String, Object> addAdminRole(@RequestBody Gw_admin admin) {
        Map<String, Object> map = new HashMap<>();
        map = adminServe.addAdminRole(admin.getAccount(), admin.getRoleId());
        return map;
    }

    //删除账号角色
    @RequiresPermissions("handleAdmin")
    @RequestMapping(value = "deleteAdminRole", method = RequestMethod.POST)
    public Map<String, Object> deleteAdminRole(@RequestBody Gw_admin admin) {
        Map<String, Object> map = new HashMap<>();
        map = adminServe.deleteAdminRole(admin.getAccount(), admin.getRoleId());
        return map;
    }

    //判断是否登录
    @RequestMapping(value = "isAuth", method = RequestMethod.POST)
    public Map<String, Object> isAuth(HttpSession session) {
        Map<String, Object> map = adminServe.isAuth(session);
        return map;
    }

    //查询账号的基本信息
    @RequestMapping(value = "queryAdminInfo", method = RequestMethod.POST)
    public Map<String, Object> queryAdminInfo(@RequestBody Gw_admin admin) {
        Map<String, Object> map = adminServe.queryAdminInfo(admin.getAccount());
        return map;
    }

    //退出登录
    @RequestMapping(value = "loginOut", method = RequestMethod.POST)
    public Map<String, Object> loginOut() {
        Map<String, Object> map = adminServe.loginOut();
        return map;
    }

    //修改用户信息
    @RequestMapping(value = "updateAdminInfo", method = RequestMethod.POST)
    public Map<String, Object> updateAdminInfo(@RequestParam(value = "file", required = false) MultipartFile file,
                                               @RequestParam(value = "filename", required = false) String filename,
                                               @RequestParam(value = "username", required = false) String username,
                                               @RequestParam(value = "gender", required = false) int gender,
                                               @RequestParam(value = "province", required = false) String province,
                                               @RequestParam(value = "city", required = false) String city,
                                               @RequestParam(value = "area", required = false) String area,
                                               @RequestParam(value = "detailAdd", required = false) String detailAdd,
                                               @RequestParam(value = "phone", required = false) String phone,
                                               @RequestParam(value = "account", required = false) String account) {
        Map<String, Object> map = adminServe.updateAdminInfo(file, filename, username, gender, province, city, area, detailAdd, phone, account);
        return map;
    }
}