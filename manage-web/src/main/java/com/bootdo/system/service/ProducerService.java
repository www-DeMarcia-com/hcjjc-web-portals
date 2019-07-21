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
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.domain.ProducerDO;
import com.bootdo.system.domain.UserDO;

@Service
public interface ProducerService {
	ProducerDO get(Long id);

	List<ProducerDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(ProducerDO kinds);

	int update(ProducerDO kinds);

	int remove(Long id);
	
	int batchRemove(Long[] ids);

}
