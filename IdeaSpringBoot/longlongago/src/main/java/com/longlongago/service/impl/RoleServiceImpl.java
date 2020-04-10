package com.longlongago.service.impl;

import com.longlongago.entity.Role;
import com.longlongago.mapper.RoleMapper;
import com.longlongago.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<String> queryRoleByUserId(String account) {
        List<Role> rolesList=roleMapper.queryRoleByAccount(account);
        List<String> roles=new ArrayList<>();
        for(Role role:rolesList){
            roles.add(role.getRolename());
        }
        return roles;
    }
}
