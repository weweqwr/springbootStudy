package com.longer.demo.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.longer.demo.utils.ActiveUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;


/**
 * 自定义过滤器记住我的功能处理session内容丢失的问题
 **/
public class RemberMeFilter extends FormAuthenticationFilter {
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue)
            throws Exception {
        //得到主体
        Subject subject = SecurityUtils.getSubject();
        //得到session
        Session session = subject.getSession();
        if (!subject.isAuthenticated() && subject.isRemembered() && session.getAttribute("user") == null) {//没有认证，但是有记住我的功能
            ActiveUser activeUser = (ActiveUser) subject.getPrincipal();
            session.setAttribute("user", activeUser.getUser());
        }
        return true;//作完上面的事情就放行了，不会直接拦截
    }

}
