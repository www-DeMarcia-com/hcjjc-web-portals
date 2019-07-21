package com.hsjjc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;
import com.hsjjc.model.Commodity;
import com.hsjjc.repository.ComodityRepository;
import com.hsjjc.service.CommodityService;
@Service
public class CommodityServiceImpl implements CommodityService{
	@Autowired
	ComodityRepository repository;
	@Override
	public Page<Commodity> findAllCommodity(Pageable pageable) {
		Page<Commodity> allList=repository.findAll(pageable);
		return allList;
	}

	@Override
	public Commodity findOneById(Long id) {
		return repository.findId(id);
	}

	@Override
	public List<Commodity> findByIshost() {
		return repository.findIshot();
	}

	@Override
	public List<Commodity> findByIspromotionNotIshot() {
		return repository.findByIspromotionNotIshot();
	}

	@Override
	public List<Commodity> findByIsNotHot() {
		return repository.findByIsNotHot();
	}

	@Override
	public List<Commodity> findByIsHotAll(int page,int size) {
		Sort sort=new Sort(Sort.Direction.DESC, "createtime");
		Pageable pageable = new PageRequest(page, size, sort);
		return repository.findIshotAll(pageable);
	}
	@Override
	public List<Commodity> findByIsHotAll(Pageable pageable) {
		return repository.findbyhot(pageable);
		
	}

	@Override
	public List<Commodity> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Commodity> findByCommoditykindsid(Long kindsid,int page,int size) {
		Sort sort=new Sort(Sort.Direction.DESC, "createtime");
		Pageable pageable = PageRequest.of(page, size, sort);
		return repository.findByCommokindsid(kindsid, pageable);
	}

	@Override
	public List<Commodity> findByCommoditykindsid(Long kindsid) {
		return repository.findByCommokindsidAll(kindsid);
	}

	@Override
	public List<Commodity> findAllByFk(String keywords, Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		return repository.findAllByFk(keywords,pageable);
	}
	
	@Override
	public List<Commodity> findAllByFk(String keywords) {
		return repository.findAllByFk(keywords);
	}

	

}
