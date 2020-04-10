package com.longlongago.service;

import com.longlongago.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PermissionService {
    //根据account查询其权限
    List<String> queryPermissionByAccount(@Param("account")String account);
}
