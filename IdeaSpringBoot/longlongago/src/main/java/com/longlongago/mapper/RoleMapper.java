package com.longlongago.mapper;

import com.longlongago.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface RoleMapper {
    //根据userid查询该用户角色
   List<Role> queryRoleByAccount(@Param("account")String account);
}
