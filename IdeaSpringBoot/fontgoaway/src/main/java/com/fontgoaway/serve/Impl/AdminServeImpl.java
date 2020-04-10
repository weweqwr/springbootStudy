package com.fontgoaway.serve.Impl;

import com.fontgoaway.entity.Gw_admin;
import com.fontgoaway.entity.Gw_role;
import com.fontgoaway.mapper.AdminMapper;
import com.fontgoaway.serve.AdminServe;
import com.fontgoaway.serve.RoleServe;
import com.fontgoaway.utils.ActiveUser;
import com.fontgoaway.utils.MultipartFileToFile;
import com.obs.services.ObsClient;
import com.obs.services.model.PutObjectResult;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
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
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            Gw_admin admin = activeUser.getAdmin();
            //判断是否被超级管理员放行
            if (admin.getState() == 1) {
                session.setAttribute("ActiveUser", activeUser);
                map.put("token", token);
                map.put("flag", 1);
                return map;
            } else if(admin.getState() !=1||admin.getVerify()!=1) {

                map.put("flag", 4);
            }

            return map;

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
                Gw_admin admin = activeUser.getAdmin();
                //判断是否被超级管理员放行
                if (admin.getState() == 1) {
                    session.setAttribute("ActiveUser", activeUser);
                    map.put("token", token);
                    map.put("flag", 1);
                    return map;
                } else {
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
    public Map<String, Object> queryAllAdmin(int verify, String account, int curPage) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = adminMapper.count(verify, account, -1);
            int pageNumber = (int) Math.ceil((double) count / 15);
            int page = (curPage - 1) * 15;
            List<Gw_admin> admins = adminMapper.queryAllAdmin(verify, account, page);
            map.put("pageNumber", pageNumber);
            map.put("count", count);
            map.put("admins", admins);
            map.put("msg", "查询成功");
            map.put("flag", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败");
            map.put("flag", 0);
        }
        return map;
    }

    @Override
    public Map<String, Object> handleVertity(int id, int state) {
        Map<String, Object> map = new HashMap<>();
        try {
            adminMapper.handleVertity(id, state);
            map.put("msg", "审核成功");
            map.put("flag", 1);
        } catch (Exception e) {
            map.put("msg", "审核失败");
            map.put("flag", 0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> queryAdminRole(int curPage, String account) {
        Map<String, Object> map = new HashMap<>();
        try {
            int count = adminMapper.count(1, null, 1);
            int pageNumber = (int) Math.ceil((double) count / 1);//向上取整
            int page = (curPage - 1) * 1; //一条数据一页
            List<Gw_admin> admins = adminMapper.queryAdminRole(page, account);
            for (int i = 0; i < admins.size(); i++) {
                admins.get(i).setRoleIds(admins.get(i).getRoleId().split(","));
            }
            for (int i = 0; i < admins.size(); i++) {
                admins.get(i).setRolenames(admins.get(i).getRolename().split(","));
            }
            map.put("admins", admins);
            map.put("pageNumber", pageNumber);
            map.put("count", count);
            map.put("msg", "查询成功");
            map.put("flag", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询成功");
            map.put("flag", 1);
        }
        return map;
    }

    @Override
    public Map<String, Object> addAdminRole(String account, String roleId) {
        Map<String, Object> map = new HashMap<>();
        try {
            adminMapper.addAdminRole(account, roleId);
            map.put("msg", "添加成功");
            map.put("flag", 1);
        } catch (Exception e) {
            map.put("msg", "添加失败");
            map.put("flag", 0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> deleteAdminRole(String account, String roleId) {
        Map<String, Object> map = new HashMap<>();
        try {
            adminMapper.deleteAdminRole(account, roleId);
            map.put("msg", "删除成功");
            map.put("flag", 1);
        } catch (Exception e) {
            map.put("msg", "删除失败");
            map.put("flag", 0);
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> isAuth(HttpSession session) {
        Map<String, Object> map = new HashMap<>();
        try {
            //1、得到主体
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                ActiveUser activeUser = (ActiveUser) session.getAttribute("ActiveUser");
                map.put("msg", "以登录");
                map.put("flag", 1);
            } else {
                map.put("msg", "未登录");
                map.put("flag", 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "未登录");
            map.put("flag", 0);
        }
        return map;
    }

    @Override
    public Map<String, Object> queryAdminInfo(String account) {
        Map<String, Object> map = new HashMap<>();
        try {
            List<Gw_admin> adminPemission = adminMapper.queryAdminInfo(account);
            map.put("admin", adminPemission);
            map.put("msg", "查询成功");
            map.put("flag", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "查询失败");
            map.put("flag", 0);
        }
        return map;
    }

    @Override
    public Map<String, Object> loginOut() {
        Map<String, Object> map = new HashMap<>();
        try {
            //1、得到主体
            Subject subject=SecurityUtils.getSubject();
            subject.logout();
            map.put("msg", "退出成功");
            map.put("flag", 1);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("msg", "退出失败");
            map.put("flag", 0);
        }
        return map;
    }

    @Override
    public Map<String, Object> updateAdminInfo(MultipartFile avatarUrl,String filename, String username, int gender, String province, String city, String area, String detailAdd, String phone, String account) {
        Map<String,Object> map=new HashMap<>();
        try {
            String imageUrl=null;
            if(avatarUrl!=null) {
                String endPoint = "obs.cn-south-1.myhuaweicloud.com";
                String ak = "BLT3FQKF02NW5BA0PYWE";
                String sk = "kCFwHRccrbAmouJFERKYRv3LsON9zqDJTqmss2SF";
                ObsClient obsClient = new ObsClient(ak, sk, endPoint);
                SimpleDateFormat fo = new SimpleDateFormat("yyyyMMddHHmmSSss");//生成时间戳
                String flag = fo.format(new Date());
                String FileRoth = "goaway/adminAvatarUrl/" + account + "/" + flag + "/";
                MultipartFileToFile mf = new MultipartFileToFile();
                File file = mf.multipartFileToFile(avatarUrl);
                PutObjectResult result = obsClient.putObject("longlongago", FileRoth + filename, file);
                imageUrl= result.getObjectUrl();
                obsClient.close();
            }
            adminMapper.updateAdminInfo(imageUrl,username,gender,province,city,area,detailAdd,phone,account);
            map.put("msg","修改成功");
            map.put("flag",1);
        }catch (Exception e){
            map.put("msg","修改失败");
            map.put("flag",0);
            e.printStackTrace();
        }
        return map;
    }
}
