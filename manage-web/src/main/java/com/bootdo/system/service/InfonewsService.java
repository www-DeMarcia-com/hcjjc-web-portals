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
import com.bootdo.system.domain.UserDO;

@Service
public interface InfonewsService {
	InfoNewsDO get(Long id);

	List<InfoNewsDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(InfoNewsDO kinds);

	int update(InfoNewsDO kinds);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

}
