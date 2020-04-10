package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_permission;
import com.fontgoaway.mapper.PermissionMapper;
import com.fontgoaway.serve.PermissionServe;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PermissionServeImpl implements PermissionServe {
    @Autowired
    PermissionMapper permissionMapper;
    @Override
    public List<String> queryPermissionByAccount(String account) {
        List<Gw_permission> permissionList=permissionMapper.queryPermissionByAccount(account);
        List<String> permissions=new ArrayList<>();
        for(Gw_permission permission:permissionList){
            permissions.add(permission.getPercode());
        }
        return permissions;
    }

    @Override
    public Map<String, Object> deleteRolePermissionByRoleId(int roleId,int perId) {
        Map<String, Object> map=new HashMap<>();
        try {
            permissionMapper.deleteRolePermissionByRoleId(roleId,perId);
            map.put("msg","删除成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","删除成功");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> addRolePermission(int roleId, int perId) {
        Map<String,Object> map=new HashMap<>();
        try {
            permissionMapper.addRolePermission(roleId,perId);
            map.put("msg","添加成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","添加失败");
            map.put("flag",0);
        }
        return map;
    }


}
