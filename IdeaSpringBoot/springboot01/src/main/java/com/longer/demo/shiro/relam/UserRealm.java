package com.longer.demo.shiro.relam;

import com.longer.demo.pojo.User;
import com.longer.demo.service.PermissionService;
import com.longer.demo.service.RoleService;
import com.longer.demo.service.UserService;
import com.longer.demo.utils.ActiveUser;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
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
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * 处理认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = token.getPrincipal().toString();//获取用户名
        Object credenticals = token.getCredentials();//获取用户密码
        //根据用户名查询用户存在不存在
        User user = this.userService.queryUserByUserName(username);
        if (user != null) {
            //根据用户id查询又哪些角色
            List<String> roles = roleService.queryRolesByUserId(user.getUserid());
            //根据用户id查询又哪些权限
            List<String> permission = permissionService.queryPermissionsByUserId(user.getUserid());

            ActiveUser activeUser = new ActiveUser(user, roles, permission);
            //用户名+地址作为md5加密的盐
            ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUsername() + user.getAddress());

            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(activeUser, user.getUserpwd(), credentialsSalt, this.getName());
            System.out.println(info);
            return info;
        }
        return null;
    }

    /**
     * 处理权限
     **/

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ActiveUser activeUser = (ActiveUser) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //根据用户名id查询又哪些角色
        List<String> roles = activeUser.getRoles();
        //如果角色不为空添加角色
        if (null != roles && roles.size() > 0) {
            info.addRoles(roles);
        }
        //根据用户id查询有哪些权限
        List<String> permissions = activeUser.getPermissions();
        if (null != permissions && permissions.size() > 0) {
            //添加权限
            info.addStringPermissions(permissions);
        }
        return info;
    }


}
