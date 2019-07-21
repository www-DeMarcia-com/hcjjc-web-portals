package com.bootdo.system.dao;

import com.bootdo.system.domain.CommodityKindsDO;
import com.bootdo.system.domain.CompanyDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CommodityKindsDao {

	CommodityKindsDO get(Long id);

	int save(CommodityKindsDO CommodityKinds);
	
	int update(CommodityKindsDO CommodityKinds);
	
	int remove(Long id);
	
	int count(Map<String,Object> map);
	
	//List<CommodityKindsDO>queryAll();
	List<CommodityKindsDO> list(Map<String,Object> map);
	
	int batchRemove(Long[] ids);
}
