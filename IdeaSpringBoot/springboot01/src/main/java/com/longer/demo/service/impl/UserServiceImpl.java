package com.longer.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longer.demo.mapper.UserMapper;
import com.longer.demo.pojo.User;
import com.longer.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User queryUserByUserName(String username) {
        // TODO Auto-generated method stub
        User user = userMapper.queryUserByUserName(username);

        return user;
    }

}
