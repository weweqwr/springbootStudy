package com.longer.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.longer.demo.pojo.Role;

@Mapper
public interface RoleMapper {

    List<Role> queryRolesByUserId(@Param("userid") Integer userid);
}