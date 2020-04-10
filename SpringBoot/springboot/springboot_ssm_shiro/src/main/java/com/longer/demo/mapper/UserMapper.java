package com.longer.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.longer.demo.pojo.User;

@Mapper
public interface UserMapper {

	User queryUserByUserName(@Param("username")String username);
}