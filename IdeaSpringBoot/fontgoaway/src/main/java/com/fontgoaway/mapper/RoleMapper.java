package com.fontgoaway.mapper;

import com.fontgoaway.entity.Gw_role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.PermitAll;
import java.util.List;

@Mapper
public interface RoleMapper {
    //根据userid查询该用户角色
    List<Gw_role> queryRoleByAccount(@Param("account")String account);
    //查询全部角色
    List<Gw_role> queryAllRole(@Param("page")int page,@Param("roleId")int roleId);
    //查询全部角色的数量
    int count(@Param("roleId")int roleId);
    //处理角色状态
    void handleRoleState(@Param("roleId")int roleId,@Param("state")int state);
    //查找该角色没有的权限
    List<Gw_role> queryRoleNoPermission(@Param("roleId")int roleId);
    //添加角色
    void addRole(@Param("rolename")String rolename);

}
