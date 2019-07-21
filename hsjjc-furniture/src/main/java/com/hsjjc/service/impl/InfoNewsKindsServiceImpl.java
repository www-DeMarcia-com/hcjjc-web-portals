package com.hsjjc.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.hsjjc.model.InfoNews;
import com.hsjjc.model.InfoNewsKinds;
import com.hsjjc.repository.InfoNewsKindsRepository;
import com.hsjjc.repository.InfoNewsRepository;
import com.hsjjc.service.InfoNewsKindsService;
import com.hsjjc.service.InfoNewsService;


@Service
public class InfoNewsKindsServiceImpl implements InfoNewsKindsService{
	@Autowired
	InfoNewsKindsRepository repository;

	@Override
	public List<InfoNewsKinds> queryAllInfoNewsKinds() {
		Sort sort =new Sort(Direction.ASC, "id");
		return repository.findAll(sort);
	}
}
