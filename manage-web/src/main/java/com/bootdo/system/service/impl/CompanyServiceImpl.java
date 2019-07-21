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
import com.bootdo.system.dao.CompanyDao;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.CompanyDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.CompanyService;
import com.bootdo.system.service.UserService;

@Transactional
@Service
public class CompanyServiceImpl implements CompanyService {
	/*@Autowired
	UserDao userMapper;
	@Autowired
	UserRoleDao userRoleMapper;
	@Autowired
	DeptDao deptMapper;*/
	@Autowired
	CompanyDao companyDao;
	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Override
	public CompanyDO get(Long id) {
		return companyDao.get(id);
	}

	@Override
	public List<CompanyDO> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int save(CompanyDO company) {
		return companyDao.save(company);
	}

	@Override
	public int update(CompanyDO company) {
		return companyDao.update(company);
	}

	@Override
	public int remove(Long companyId) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
