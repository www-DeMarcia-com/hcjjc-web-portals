package com.bootdo.system.service;

import java.util.List;
import java.util.Map;

import com.bootdo.system.domain.UserRoleDO;

public interface UserRoleService {
	UserRoleDO userrole(Long id);
	int save (UserRoleDO user);
	int delete(Long id);
	List<UserRoleDO> list(Map<String, Object> map);
}
