package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_admin;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface AdminServe {
    //根据account获取用户信息(shiro)
    Gw_admin admin(String account);
    //登录
    Map<String,Object> doLogin(String account, String password, HttpSession session);
    //超级管理员登录
    Map<String,Object> superLogin(String account, String password, HttpSession session);
    //注册
    Map<String,Object> doRegister(String account, String password);
    //查询所有管理员账号
    Map<String,Object> queryAllAdmin(int verify,String account,int curPage);
    //处理审核
    Map<String,Object>  handleVertity(int id,int state);
    //查询该账号拥有的角色
    Map<String,Object> queryAdminRole(int curPage,String account);
    //给账号添加角色
    Map<String,Object> addAdminRole(String account,String roleId);
    //删除账号角色
    Map<String,Object> deleteAdminRole(String account,String roleId);
    //判断是否登录
    Map<String,Object> isAuth(HttpSession session);
    //查询账号的权限
    Map<String,Object> queryAdminInfo(@Param("account")String account);
    //退出登录
    Map<String,Object> loginOut();
    //修改用户信息
    Map<String, Object> updateAdminInfo(MultipartFile avatarUrl,String filename, String username, int gender, String province, String city, String area, String detailAdd, String phone, String account);

}
