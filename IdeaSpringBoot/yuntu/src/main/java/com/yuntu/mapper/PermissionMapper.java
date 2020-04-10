package com.yuntu.mapper;

import com.yuntu.entity.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PermissionMapper {
    List<Permission> query(@Param("perid") Integer perid);
}
