package com.bootdo.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.BuildTree;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.UserRoleService;
import com.bootdo.system.service.UserService;

@Transactional
@Service
public class UserRoleServiceImpl implements UserRoleService {
	
	@Autowired
	UserRoleDao userRoleMapper;
	
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public UserRoleDO userrole(Long id) {
		return userRoleMapper.get(id);
	}

	@Override
	public int save(UserRoleDO user) {
		logger.info("奇怪消失。。。。。。。。。。。。。。。。。。。。。。。。。。。。");
		return userRoleMapper.save(user);
	}

	@Override
	public int delete(Long id) {
		return userRoleMapper.remove(id);
	}
	public List<UserRoleDO> list(Map<String, Object> map){
		return userRoleMapper.list(map);
	}



}
