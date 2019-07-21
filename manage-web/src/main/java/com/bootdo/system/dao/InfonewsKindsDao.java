package com.bootdo.system.dao;

import com.bootdo.system.domain.InfonewsKindsDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface InfonewsKindsDao {

	InfonewsKindsDO get(Long id);

	int save(InfonewsKindsDO CommodityKinds);
	
	int update(InfonewsKindsDO CommodityKinds);
	
	int remove(Long id);
	
	int count(Map<String,Object> map);
	
	
	List<InfonewsKindsDO> list(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
