package com.yuntu.service.impl;

import com.yuntu.entity.Permission;
import com.yuntu.mapper.PermissionMapper;
import com.yuntu.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionImpl implements PermissionService {
    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public List<String> query(Integer perid) {
        List<Permission> permissionList = permissionMapper.query(perid);
        List<String> permissions = new ArrayList<>();
        for (int i = 0; i < permissionList.size(); i++) {
            permissions.add(permissionList.get(i).getPername());
        }
        return permissions;
    }
}
