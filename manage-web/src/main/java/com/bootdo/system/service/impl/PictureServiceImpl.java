package com.bootdo.system.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootdo.system.dao.InfonewsDao;
import com.bootdo.system.dao.InfonewsKindsDao;
import com.bootdo.system.dao.PictureDao;
import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.domain.PictureDO;
import com.bootdo.system.service.InfonewsKindsService;
import com.bootdo.system.service.InfonewsService;
import com.bootdo.system.service.PictureService;

@Transactional
@Service
public class PictureServiceImpl implements PictureService {
	@Autowired
	private PictureDao kindsDao;
	@Override
	public PictureDO get(Long id) {
		return kindsDao.get(id);
	}

	@Override
	public List<PictureDO> list(Map<String, Object> map) {
		return kindsDao.list(map);
	}

	@Override
	public int save(PictureDO kinds) {
		return kindsDao.save(kinds);
	}

	@Override
	public int update(PictureDO kinds) {
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

	@Override
	public int getMaxRank(Long commodityId) {
		return kindsDao.getMaxRank(commodityId);
	}	

}
