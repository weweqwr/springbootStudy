package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_role;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RoleServe {
    //根据account查询该用户角色
    List<String> queryRoleByAccount(String account);
    //查询全部角色
    Map<String,Object> queryAllRole(int curPage,int roleId);
    //处理角色状态
    Map<String,Object> handleRoleState(int roleId,int state);
    //处理该角色没有的权限
    Map<String,Object> queryRoleNoPermission(int roleId);
    //添加角色
    Map<String,Object> addRole(@Param("rolename")String rolename);


}
