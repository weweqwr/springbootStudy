package com.longer.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longer.demo.mapper.PermissionMapper;
import com.longer.demo.pojo.Permission;
import com.longer.demo.service.PermissionService;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<String> queryPermissionsByUserId(Integer userid) {

        List<Permission> permissionList = permissionMapper.queryPermissionsByUserId(userid);
        List<String> permissions = new ArrayList<String>();

        for (Permission permission : permissionList) {
            permissions.add(permission.getPercode());
        }
        return permissions;
    }

}
