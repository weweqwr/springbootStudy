package com.longer.demo.servicedao;

import com.longer.demo.pojo.User;

public interface ServiceDao {
	public User find(String name, String password);
}
