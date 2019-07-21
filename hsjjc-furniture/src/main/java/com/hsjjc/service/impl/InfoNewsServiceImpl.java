package com.hsjjc.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.hsjjc.model.InfoNews;
import com.hsjjc.model.InfoNewsKinds;
import com.hsjjc.repository.InfoNewsKindsRepository;
import com.hsjjc.repository.InfoNewsRepository;
import com.hsjjc.service.InfoNewsService;


@Service
public class InfoNewsServiceImpl implements InfoNewsService{
	@Autowired
	InfoNewsRepository repository;
//	@Autowired
//	InfoNewsKindsRepository krepository;
	public Page<InfoNews> queryAllInfoNews(int page,int size) {
		Sort sort=new Sort(Sort.Direction.DESC, "time");
		Pageable pageable = new PageRequest(page, size, sort);/*new PageRequest(page, size, Sort.Direction.DESC, "time"); */
		return repository.findAll(pageable);
	
	}

	@Override
	public InfoNews queryInfoByid(Long id) {
		InfoNews info=repository.getOne(id)/*findOne(id)*/;
		return info;
	}

	@Override
	public List<InfoNews> queryInfoByKinds(int page,int size,Long kindsid) {
		Sort sort=new Sort(Sort.Direction.DESC, "time");
		Pageable pageable = new PageRequest(page, size, sort);/*new PageRequest(page, size, Sort.Direction.DESC, "time"); */
		List<InfoNews> lists=repository.findBykindsid(kindsid,pageable);
		return lists;
	}

	@Override
	public List<InfoNews> queryAll() {
		Sort sort=new Sort(Sort.Direction.DESC, "time");
		List<InfoNews>allList=repository.findAll(sort);
		return allList;
	}

	@Override
	public List<InfoNews> queryInfoByKinds(Long kindsid) {
		return repository.findBykindsid(kindsid);
	}

	@Override
	public List<InfoNews> queryNwesByKey(String key) {
		return repository.findByKey(key);
	}

	@Override
	public List<InfoNews> queryNwesByKey(String key,Integer page, Integer size) {
		Pageable pageable = new PageRequest(page, size);
		return repository.findByKeyPage(key, pageable);
	}

	@Override
	public InfoNews insertNews(InfoNews info) {
		
		return repository.saveAndFlush(info);
	}

	@Override
	public Long findMax() {
		
		return repository.findMax();
	}

	@Override
	public Long findMin() {
		
		return repository.findMin();
	}

	/*@Override
	public Integer queryRownumByid(Long id) {
		return repository.findRownumById(id);
	}

	@Override
	public InfoNews queryUp(Integer rownum) {
		return repository.findByIdUp(rownum);
	}

	@Override
	public InfoNews queryDown(Integer rownum) {
		return repository.findByIdDown(rownum);
	}*/

}
