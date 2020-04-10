package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
    //根据account查询其权限
    List<Gw_permission> queryPermissionByAccount(@Param("account")String account);
    //删除角色的权限
    void deleteRolePermissionByRoleId(@Param("roleId")int roleId,@Param("perId")int perId);
    //添加权限
    void addRolePermission(@Param("roleId")int roleId,@Param("perId")int perId);
    //导航栏的分配
    List<Gw_permission> queryPermissionNav(@Param("account")String account);
}
