package com.longer.demo.servicedaoimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longer.demo.pojo.User;
import com.longer.demo.servicedao.ServiceDao;
import com.longer.demo.userdao.UserDao;

@Service
public class ServiceDaoImpl implements ServiceDao {
	@Autowired
	private UserDao userDao;

	@Override
	public User find(String name, String password) {
		// TODO Auto-generated method stub
		return userDao.find(name, password);
	}

}
