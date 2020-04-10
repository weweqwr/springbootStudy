package com.fontgoaway.serve;

import com.fontgoaway.entity.Gw_permission;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PermissionServe {
    //根据account查询其权限
    List<String> queryPermissionByAccount(String account);
    //删除角色的权限
    Map<String,Object> deleteRolePermissionByRoleId(int roleId,int perId);
    //添加权限
    Map<String,Object>  addRolePermission(int roleId,int perId);
}
