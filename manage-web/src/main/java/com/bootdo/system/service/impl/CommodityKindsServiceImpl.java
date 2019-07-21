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
import com.bootdo.system.dao.CommodityKindsDao;
import com.bootdo.system.dao.CompanyDao;
import com.bootdo.system.dao.DeptDao;
import com.bootdo.system.dao.UserDao;
import com.bootdo.system.dao.UserRoleDao;
import com.bootdo.system.domain.CommodityKindsDO;
import com.bootdo.system.domain.CompanyDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.CommodityKindsService;
import com.bootdo.system.service.CompanyService;
import com.bootdo.system.service.UserService;

@Transactional
@Service
public class CommodityKindsServiceImpl implements CommodityKindsService {
	@Autowired
	private CommodityKindsDao kindsDao;
	@Override
	public CommodityKindsDO get(Long id) {
		return kindsDao.get(id);
	}

	@Override
	public List<CommodityKindsDO> list(Map<String, Object> map) {
		return kindsDao.list(map);
	}

	@Override
	public int save(CommodityKindsDO kinds) {
		return kindsDao.save(kinds);
	}

	@Override
	public int update(CommodityKindsDO kinds) {
		return kindsDao.update(kinds);
	}

	@Override
	public int remove(Long id) {
		return kindsDao.remove(id);
	}

	@Override
	public int count(Map<String, Object> map) {
		return kindsDao.count(map);
	}
	
	@Override
	public int batchRemove(Long[] ids){
		return kindsDao.batchRemove(ids);
	}
	
	
	
	

}
