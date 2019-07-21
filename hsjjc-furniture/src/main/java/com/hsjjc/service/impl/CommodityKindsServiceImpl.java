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
import com.hsjjc.model.CommodityKinds;
import com.hsjjc.repository.ComodityKindsRepository;
import com.hsjjc.repository.ComodityRepository;
import com.hsjjc.service.CommodityKindsService;
import com.hsjjc.service.CommodityService;
@Service
public class CommodityKindsServiceImpl implements CommodityKindsService{
	@Autowired
	ComodityKindsRepository repository;
	@Override
	public List<CommodityKinds> findAllCommodityKinds() {
		Sort sort=new Sort(Direction.ASC, "id");
		return repository.findAll(sort);
	}
}
