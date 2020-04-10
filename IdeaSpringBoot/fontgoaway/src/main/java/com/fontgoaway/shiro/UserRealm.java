package com.fontgoaway.shiro;

import com.fontgoaway.entity.Gw_admin;
import com.fontgoaway.serve.AdminServe;
import com.fontgoaway.serve.PermissionServe;
import com.fontgoaway.serve.RoleServe;
import com.fontgoaway.utils.ActiveUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Autowired
    private AdminServe adminServe;
    @Autowired
    private RoleServe roleServe;
    @Autowired
    private PermissionServe permissionServe;

    /**
     * 这是处理认证
     **/


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = token.getPrincipal().toString();//账号
        Object credenticals = token.getCredentials();//获取密码
        //根据用户名查询用户是否存在
        Gw_admin admin = this.adminServe.admin(account);
        if (admin != null) {
            //根据账号查询用户有哪些角色
            List<String> roles = roleServe.queryRoleByAccount(account);
            //根据账号查询用户有哪些权限
            List<String> permissions = permissionServe.queryPermissionByAccount(account);
            //动态类缓存用户信息
            ActiveUser activeUser = new ActiveUser(admin, roles, permissions);
            //用户名作为md5加密的盐
            ByteSource credentialsSalt = ByteSource.Util.bytes(admin.getAccount());

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, admin.getPassword(), credentialsSalt, this.getName());
            return info;
        }

        return null;
    }

    /**
     * 这是处理权限
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户名id查询有哪些角色
        List<String> roles = activeUser.getRoles();
        //如果角色不为空添加角色
        if (null != roles && roles.size() > 0) {
            info.addRoles(roles);
        }
        //根据id查询有哪些权限
        List<String> permissions = activeUser.getPermissions();
        if (null != permissions && permissions.size() > 0) {
            //添加权限
            info.addStringPermissions(permissions);
        }
        return info;
    }

}
