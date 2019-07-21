package com.bootdo.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.system.dao.CommodityDao;
import com.bootdo.system.dao.InfonewsDao;
import com.bootdo.system.dao.InfonewsKindsDao;
import com.bootdo.system.domain.CommodityDO;
import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.service.CommodityService;
import com.bootdo.system.service.InfonewsKindsService;
import com.bootdo.system.service.InfonewsService;

@Transactional
@Service
public class CommodityServiceImpl implements CommodityService {
	@Autowired
	private CommodityDao kindsDao;
	@Override
	public CommodityDO get(Long id) {
		return kindsDao.get(id);
	}

	@Override
	public List<CommodityDO> list(Map<String, Object> map) {
		return kindsDao.list(map);
	}

	@Override
	public int save(CommodityDO kinds) {
		return kindsDao.save(kinds);
	}

	@Override
	public int update(CommodityDO kinds) {
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
