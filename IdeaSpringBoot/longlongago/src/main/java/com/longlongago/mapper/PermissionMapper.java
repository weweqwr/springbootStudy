package com.longlongago.mapper;

import com.longlongago.entity.Permission;
import com.longlongago.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface PermissionMapper {
    //根据account查询其权限
    List<Permission> queryPermissionByAccount(@Param("account")String account);

}
