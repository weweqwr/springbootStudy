package com.longlongago.service.impl;

import com.longlongago.entity.Permission;
import com.longlongago.entity.Role;
import com.longlongago.mapper.PermissionMapper;
import com.longlongago.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<String> queryPermissionByAccount(String account) {
        List<Permission> permissionList=permissionMapper.queryPermissionByAccount(account);
        List<String> permissions=new ArrayList<>();
        for(Permission permission:permissionList) {
            permissions.add(permission.getPercode());
        }
        return permissions;
    }
}
