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
import com.bootdo.system.domain.UserDO;

@Service
public interface CommodityKindsService {
	CommodityKindsDO get(Long id);

	List<CommodityKindsDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(CommodityKindsDO kinds);

	int update(CommodityKindsDO kinds);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

}
