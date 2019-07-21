package com.bootdo.system.dao;

import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.domain.ProducerDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProducerDao {

	ProducerDO get(Long id);

	int save(ProducerDO CommodityKinds);
	
	int update(ProducerDO CommodityKinds);
	
	int remove(Long id);
	
	int count(Map<String,Object> map);
	
	
	List<ProducerDO> list(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
