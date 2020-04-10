package com.longer.demo.service;

import java.util.List;

public interface PermissionService {
	List<String> queryPermissionsByUserId(Integer userid);
}
