package com.bootdo.system.dao;

import com.bootdo.system.domain.CommodityDO;
import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommodityDao {

	CommodityDO get(Long id);

	int save(CommodityDO CommodityKinds);
	
	int update(CommodityDO CommodityKinds);
	
	int remove(Long id);
	
	int count(Map<String,Object> map);
	
	
	List<CommodityDO> list(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
