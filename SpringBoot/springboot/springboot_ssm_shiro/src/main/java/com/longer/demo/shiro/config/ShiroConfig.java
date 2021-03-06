package com.longer.demo.shiro.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.longer.demo.shiro.relam.UserRealm;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configuration
public class ShiroConfig {

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	@Bean
	@ConditionalOnMissingBean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
		defaultAAP.setProxyTargetClass(true);
		return defaultAAP;
	}

	// 注入凭证配置器
	@Bean
	HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		hashedCredentialsMatcher.setHashIterations(2);
		return hashedCredentialsMatcher;
	}

	// 将兹定于relam的验证方法注入的哦容器中
	@Bean
	public UserRealm myUerRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
		// 注入凭证配置器
		UserRealm userRealm = new UserRealm();
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher);
		return userRealm;
	}

	// 权限管理，配置主要是Realm的管理认证
	@Bean
	public SecurityManager securityManager(UserRealm myUerRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myUerRealm);
		return securityManager;
	}

	// Filter工厂，设置对应的过滤条件和跳转条件
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		Map<String, String> map = new HashMap<>();

		map.put("/login.html", "anon");
		map.put("/login/login.do", "anon");
		map.put("/**", "authc");
		// 注入如果没认证 跳转的页面
		shiroFilterFactoryBean.setLoginUrl("/login.html");
		// 错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/sucess");
		// 未授权的跳转页
		shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
		return shiroFilterFactoryBean;
	}

	// 加入注解的使用，不加入这个注解不生效
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}

}
