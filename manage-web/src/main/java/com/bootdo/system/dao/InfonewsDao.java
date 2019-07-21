package com.bootdo.system.dao;

import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface InfonewsDao {

	InfoNewsDO get(Long id);

	int save(InfoNewsDO CommodityKinds);
	
	int update(InfoNewsDO CommodityKinds);
	
	int remove(Long id);
	
	int count(Map<String,Object> map);
	
	
	List<InfoNewsDO> list(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
