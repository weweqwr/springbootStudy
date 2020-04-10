package com.longer.demo.userdao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.longer.demo.pojo.User;

@Mapper
public interface UserDao {
	public User find(@Param("name")String name,@Param("password")String password);
}
