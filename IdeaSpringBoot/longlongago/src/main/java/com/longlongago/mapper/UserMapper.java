package com.longlongago.mapper;

import com.longlongago.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    //简介用户首页的个人信息
    List<User> selectUserInfo(@Param("account") String account);
    //根据account获取用户信息(shiro)
    User user(@Param("account") String account);
    //注册
     public void doRegister(@Param("account") String account, @Param("password") String password);

}
