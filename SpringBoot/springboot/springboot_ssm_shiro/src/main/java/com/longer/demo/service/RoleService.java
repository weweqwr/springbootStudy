package com.longer.demo.service;

import java.util.List;

public interface RoleService {
	List<String> queryRolesByUserId(Integer userid);
}
