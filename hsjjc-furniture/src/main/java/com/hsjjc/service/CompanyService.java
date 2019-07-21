package com.hsjjc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.Company;

public interface CompanyService {
//	public Page<Commodity> findAllCommodity(int page,int size);
	public Company findOneById(Long id);
}
