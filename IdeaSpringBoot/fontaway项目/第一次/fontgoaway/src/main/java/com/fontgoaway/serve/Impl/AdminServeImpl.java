package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_admin;
import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.mapper.AdminMapper;
import com.fontgoaway.serve.AdminServe;
import com.fontgoaway.serve.RoleServe;
import com.fontgoaway.utils.ActiveUser;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServeImpl implements AdminServe {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleServe roleServe;

    @Override
    public Gw_admin admin(String account) {
        Gw_admin admin = adminMapper.admin(account);
        return admin;
    }

    @Override
    public Map<String, Object> doLogin(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //1、得到主体
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            subject.login(token);
            if (subject.hasRole("admin")||subject.hasRole("superAdmin")) {//判断是否为管理员
                    ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
                    Gw_admin admin=activeUser.getAdmin();
                //判断是否被超级管理员放行
                if(admin.getState()==1){
                    session.setAttribute("ActiveUser", activeUser);
                    map.put("token", token);
                    map.put("flag", 1);
                    return map;
                }else {
                    map.put("flag", 4);
                }
            } else {
                map.put("flag", 3);
                return map;
            }
        } catch (AuthenticationException ae) {
            map.put("flag", 0);
        } catch (Exception e) {
            map.put("flag", 2);
            e.printStackTrace();
        }
        return map;
    }
    @Override
    public Map<String, Object> superLogin(String account, String password, HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        //1、得到主体
        Subject subject = SecurityUtils.getSubject();
        //2、封装用户名和密码
        UsernamePasswordToken token = new UsernamePasswordToken(account, password);
        try {
            subject.login(token);
            if (subject.hasRole("superAdmin")) {//判断是否为超级管理员
                ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
                Gw_admin admin=activeUser.getAdmin();
                //判断是否被超级管理员放行
                if(admin.getState()==1){
                    session.setAttribute("ActiveUser", activeUser);
                    map.put("token", token);
                    map.put("flag", 1);
                    return map;
                }else {
                    map.put("flag", 4);
                }
            } else {
                map.put("flag", 3);
                return map;
            }
        } catch (AuthenticationException ae) {
            map.put("flag", 0);
        } catch (Exception e) {
            map.put("flag", 2);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> doRegister(String account, String password) {
        Map<String, Object> map = new HashMap<>();
        try {
            Gw_admin gw_admin = adminMapper.admin(account);
            System.out.println(gw_admin == null);
            if (gw_admin == null) {
                Md5Hash md5 = new Md5Hash(password, account, 2);
                String repassword = md5.toString();
                adminMapper.doRegister(account, repassword);
                map.put("msg", "注册成功");
                map.put("flag", 1);
            } else {
                map.put("msg", "该账号以存在");
                map.put("flag", 2);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "注册失败");
            map.put("flag", 0);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryAllAdmin(int verify,String account, int curPage) {
        Map<String, Object> map=new HashMap<>();
        try {
            int count=adminMapper.count(verify,account,-1);
            int pageNumber=(int)Math.ceil((double)count/15);
            int page=(curPage-1)*15;
            List<Gw_admin> admins=adminMapper.queryAllAdmin(verify,account,page);
            map.put("pageNumber",pageNumber);
            map.put("count", count);
            map.put("admins",admins);
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
    public Map<String, Object> handleVertity(int id,int state) {
        Map<String, Object> map=new HashMap<>();
        try {
            adminMapper.handleVertity(id,state);
            map.put("msg","审核成功");
            map.put("flag",1);
        }catch (Exception e){
            map.put("msg","审核失败");
            map.put("flag",0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> queryAdminRole(int curPage,String account) {
        Map<String, Object> map=new HashMap<>();
        try {
            int count=adminMapper.count(1,null,1);
            int pageNumber=(int)Math.ceil((double)count/1);//向上取整
            int page=(curPage-1)*1; //一条数据一页
            List<Gw_admin> admins=adminMapper.queryAdminRole(page,account);
            for(int i=0;i<admins.size();i++){
                admins.get(i).setRoleIds(admins.get(i).getRoleId().split(","));
            }
            for(int i=0;i<admins.size();i++){
                admins.get(i).setRolenames(admins.get(i).getRolename().split(","));
            }
            map.put("admins",admins);
            map.put("pageNumber",pageNumber);
            map.put("count",count);
            map.put("msg","查询成功");
            map.put("flag",1);
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","查询成功");
            map.put("flag",1);
        }
        return map;
    }

    @Override
    public Map<String, Object> addAdminRole(String account, String roleId) {
        Map<String, Object> map=new HashMap<>();
        try {
            adminMapper.addAdminRole(account,roleId);
            map.put("msg","添加成功");
            map.put("flag",1);
        }catch (Exception e){
            map.put("msg","添加失败");
            map.put("flag",0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAdminRole(String account, String roleId) {
        Map<String, Object> map=new HashMap<>();
        try {
            adminMapper.deleteAdminRole(account,roleId);
            map.put("msg","删除成功");
            map.put("flag",1);
        }catch (Exception e){
            map.put("msg","删除失败");
            map.put("flag",0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> isAuth(HttpSession session) {
        Map<String,Object> map=new HashMap<>();
        try {
            //1、得到主体
            Subject subject=SecurityUtils.getSubject();
            if(subject.isAuthenticated()) {
                ActiveUser activeUser= (ActiveUser) session.getAttribute("ActiveUser");
                map.put("msg", "以登录");
                map.put("flag", 1);
            }else{
                map.put("msg","未登录");
                map.put("flag",0);
            }
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","未登录");
            map.put("flag",0);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryAdminInfo(String account) {
        Map<String,Object> map=new HashMap<>();
        try {
            map.put("msg","查询成功");
            map.put("flag",0);
        }catch (Exception e){
            map.put("msg","查询失败");
            map.put("flag",0);
        }
        return null;
    }
}
