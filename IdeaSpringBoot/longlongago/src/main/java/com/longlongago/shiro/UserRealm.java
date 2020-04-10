package com.longlongago.shiro;

import com.longlongago.entity.User;
import com.longlongago.service.PermissionService;
import com.longlongago.service.RoleService;
import com.longlongago.service.UserService;
import com.longlongago.utils.ActiveUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;

import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class UserRealm extends AuthorizingRealm {
    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 这是处理认证
     **/


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String account = token.getPrincipal().toString();//账号
        Object credenticals = token.getCredentials();//获取密码
        //根据用户名查询用户是否存在
        User user = this.userService.user(account);
        if (user != null) {
            //根据账号查询用户有哪些角色
            List<String> roles = roleService.queryRoleByUserId(account);
            //根据账号查询用户有哪些权限
            List<String> permissions = permissionService.queryPermissionByAccount(account);
            //动态类缓存用户信息
            ActiveUser activeUser = new ActiveUser(user, roles, permissions);
            //用户名作为md5加密的盐
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getAccount());

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getPassword(), credentialsSalt, this.getName());
            System.out.println(info);
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
