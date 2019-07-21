package com.bootdo.system.dao;

import com.bootdo.system.domain.CompanyDO;
import com.bootdo.system.domain.UserDO;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CompanyDao {

	CompanyDO get(Long id);

	int save(CompanyDO user);
	
	int update(CompanyDO user);
	
	int remove(Long userId);
	
	
	
}
