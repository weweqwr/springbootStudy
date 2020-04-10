package com.longer.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.longer.demo.pojo.Permission;

@Mapper
public interface PermissionMapper {

	List<Permission> queryPermissionsByUserId(@Param("userid")Integer userid);
}