package com.hsjjc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hsjjc.model.Commodity;

public interface CommodityService {
	public Page<Commodity> findAllCommodity(Pageable pageable);
	public Commodity findOneById(Long id);
	public List<Commodity> findByIshost();
	public List<Commodity> findByIspromotionNotIshot();
	public List<Commodity> findByIsNotHot();
	public List<Commodity> findByIsHotAll(int page,int size);
	public List<Commodity> findByIsHotAll(Pageable pageable);
	public List<Commodity> findAll();
	public List<Commodity> findByCommoditykindsid(Long kindsid,int page,int size);
	public List<Commodity> findByCommoditykindsid(Long kindsid);
	public List<Commodity> findAllByFk(String keywords, Integer page, Integer productsize);
	public List<Commodity> findAllByFk(String keywords);
}
