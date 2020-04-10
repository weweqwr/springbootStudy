package com.longlongago.service;

import com.longlongago.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleService {
    //根据userid查询该用户角色
    List<String> queryRoleByUserId(String account);
}
