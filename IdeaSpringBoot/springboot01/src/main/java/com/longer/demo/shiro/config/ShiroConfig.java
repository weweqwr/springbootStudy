package com.longer.demo.shiro.config;

import com.longer.demo.filter.MyFormAuthenticationFilter;
import com.longer.demo.filter.RemberMeFilter;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.longer.demo.shiro.relam.UserRealm;
import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

import javax.servlet.Filter;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //注入凭证配置器
    @Bean
    HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    //自定义relam的验证方法注入到容器中
    @Bean
    public UserRealm myUserRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        //注入凭证配置器
        UserRealm userRealm = new UserRealm();
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return userRealm;
    }

    //声明一个/cookie的对象
    @Bean
    public SimpleCookie cookie() {
        SimpleCookie cookie = new SimpleCookie("remberMe");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(604800);
        return cookie;
    }

    @Bean
    public CookieRememberMeManager cookieManager(SimpleCookie cookie) {
        CookieRememberMeManager cookieManager = new CookieRememberMeManager();
        cookieManager.setCookie(cookie);
        return cookieManager;
    }


    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager(UserRealm myUserRealm, CookieRememberMeManager cookieManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myUserRealm);
        securityManager.setRememberMeManager(cookieManager);
        return securityManager;
    }


    //Filter工厂，设置对应的过滤条件和跳转条件
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new HashMap<>();
        Map<String, Filter> maps = new HashMap<>();
        maps.put("authc", new MyFormAuthenticationFilter());
        maps.put("rememberMe", new RemberMeFilter());
        shiroFilterFactoryBean.setFilters(maps);
        map.put("/js/**", "anon");
        map.put("/index.html", "anon");
        map.put("/login/login.do", "anon");
        map.put("/login/toLogin*", "anon");
        map.put("/**", "authc");
        // 注入如果没认证 跳转的页面
        shiroFilterFactoryBean.setLoginUrl("/index.html");
        // 错误页面，认证不通过跳转
        shiroFilterFactoryBean.setUnauthorizedUrl("/index.html");
        // 未授权的跳转页
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean
    // 加入注解的使用，不加入这个注解不生效
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }


}
