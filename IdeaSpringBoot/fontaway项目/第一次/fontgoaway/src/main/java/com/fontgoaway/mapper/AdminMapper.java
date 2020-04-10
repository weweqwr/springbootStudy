package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AdminMapper {
    //根据account获取用户信息(shiro)
    Gw_admin admin(@Param("account") String account);
    //注册
    void doRegister(@Param("account")String account,@Param("password")String password);
    //查询所有管理员账号
    List<Gw_admin> queryAllAdmin(@Param("verify")int verify,@Param("account") String account,@Param("page")int page);
    //查询所有管理员总数
    int count(@Param("verify")int verify,@Param("account") String account,@Param("state")int state);
    //处理审核
    void handleVertity(@Param("id")int id,@Param("state")int state);
    //查询该账号拥有的角色
    List<Gw_admin> queryAdminRole(@Param("page")int page,@Param("account")String account);
    //给账号添加角色
    void addAdminRole(@Param("account")String account,@Param("roleId")String roleId);
    //删除账号角色
    void deleteAdminRole(@Param("account")String account,@Param("roleId")String roleId);
    //查询账号的基本信息
    List<Gw_admin> queryAdminInfo(@Param("account")String account);
}
