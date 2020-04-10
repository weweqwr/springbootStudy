package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.mapper.RoleMapper;
import com.fontgoaway.serve.RoleServe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoleServeImpl implements RoleServe {
    @Autowired
    RoleMapper roleMapper;
    @Override
    public List<String>  queryRoleByAccount(String account) {
        List<Gw_role> rolesList=roleMapper.queryRoleByAccount(account);
        List<String> roles=new ArrayList<>();
        for(Gw_role role:rolesList){
            roles.add(role.getRolename());
        }
        return roles;
    }

    @Override
    public Map<String, Object> queryAllRole(int curPage,int roleId) {
        Map<String,Object> map=new HashMap<>();
        try {
            int count=roleMapper.count(roleId);
            int pageNumber=(int)Math.ceil((double)count/1);//向上取整
            int page=(curPage-1)*1; //一条数据一页
            List<Gw_role> roles=roleMapper.queryAllRole(page,roleId);
            for(int i=0;i<roles.size();i++){
                roles.get(i).setPerIds(roles.get(i).getPerId().split(","));
            }
            for(int i=0;i<roles.size();i++){
                roles.get(i).setPerNames(roles.get(i).getPername().split(","));
            }
            map.put("roles",roles);
            map.put("pageNumber",pageNumber);
            map.put("count",count);
            map.put("msg","查询成功");

            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询失败");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> handleRoleState(int roleId, int state) {
        Map<String,Object> map=new HashMap<>();
        try {
            roleMapper.handleRoleState(roleId,state);
            map.put("msg","处理成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","处理失败");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryRoleNoPermission(int roleId) {
        Map<String,Object> map=new HashMap<>();
        try{
            List<Gw_role> noPermissionList=roleMapper.queryRoleNoPermission(roleId);
            map.put("noPermissionList",noPermissionList);
            map.put("msg","查询成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询失败");
            map.put("flag",0);
        }
        return map;
    }
    //添加角色
    @Override
    public Map<String, Object> addRole(String rolename) {
        Map<String,Object> map=new HashMap<>();
        try{
            roleMapper.addRole(rolename);
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
