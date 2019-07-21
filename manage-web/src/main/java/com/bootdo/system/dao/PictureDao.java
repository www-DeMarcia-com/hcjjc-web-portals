package com.bootdo.system.dao;

import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.domain.PictureDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface PictureDao {

	PictureDO get(Long id);

	int save(PictureDO CommodityKinds);
	
	int update(PictureDO CommodityKinds);
	
	int remove(Long id);
	
	int count(Map<String,Object> map);
	
	
	List<PictureDO> list(Map<String,Object> map);
	
	int batchRemove(Long[] ids);

	int getMaxRank(Long commodityId);
}
