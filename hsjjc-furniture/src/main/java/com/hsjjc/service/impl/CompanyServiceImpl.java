package com.hsjjc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.hsjjc.model.Commodity;
import com.hsjjc.model.Company;
import com.hsjjc.repository.CompanyRepository;
import com.hsjjc.service.CompanyService;
@Service
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	CompanyRepository rep;
	
	@Override
	public Company findOneById(Long id) {
		return rep.getOne(id)/*findOne(id)*/;
	}
	

}
