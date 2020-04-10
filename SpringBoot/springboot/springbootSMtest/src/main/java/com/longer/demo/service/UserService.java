package com.longer.demo.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longer.demo.dao.UserMapper;
import com.longer.demo.pojo.User;

@Service
@Transactional // 注解
public class UserService {
	@Resource
	private UserMapper userMapper;


	public List<User> findAll() {
		User user = new User();
		user.setName("柳岩");
		return userMapper.select(user);

	}
}

