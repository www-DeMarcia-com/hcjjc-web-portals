package com.bootdo.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.dao.CommodityKindsDao;
import com.bootdo.system.domain.CommodityKindsDO;
import com.bootdo.system.domain.CompanyDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.domain.PictureDO;
import com.bootdo.system.domain.UserDO;

@Service
public interface PictureService {
	PictureDO get(Long id);

	List<PictureDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(PictureDO kinds);

	int update(PictureDO kinds);

	int remove(Long id);
	
	int batchRemove(Long[] ids);
	
	int getMaxRank(Long commodityId);
}
