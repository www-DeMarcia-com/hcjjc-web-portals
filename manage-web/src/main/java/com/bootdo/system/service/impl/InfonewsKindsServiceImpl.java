package com.bootdo.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bootdo.system.dao.InfonewsKindsDao;
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.service.InfonewsKindsService;

@Transactional
@Service
public class InfonewsKindsServiceImpl implements InfonewsKindsService {
	@Autowired
	private InfonewsKindsDao kindsDao;
	@Override
	public InfonewsKindsDO get(Long id) {
		return kindsDao.get(id);
	}

	@Override
	public List<InfonewsKindsDO> list(Map<String, Object> map) {
		return kindsDao.list(map);
	}

	@Override
	public int save(InfonewsKindsDO kinds) {
		return kindsDao.save(kinds);
	}

	@Override
	public int update(InfonewsKindsDO kinds) {
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
