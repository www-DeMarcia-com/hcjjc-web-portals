package com.bootdo.system.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.bootdo.common.domain.Tree;
import com.bootdo.system.domain.CompanyDO;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.UserDO;

@Service
public interface CompanyService {
	CompanyDO get(Long id);

	List<CompanyDO> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(CompanyDO company);

	int update(CompanyDO company);

	int remove(Long companyId);


}
