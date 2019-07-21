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
import com.hsjjc.model.Picture;
import com.hsjjc.repository.ComodityRepository;
import com.hsjjc.repository.PictureRepository;
import com.hsjjc.service.CommodityService;
import com.hsjjc.service.PictureService;
@Service
public class PictureServiceImpl implements PictureService{
	@Autowired
	PictureRepository repository;

	@Override
	public List<Picture> picListByCommodityid(Long commodityid) {
		/*Sort sort=new Sort(Direction.ASC, "rank");
		Pageable pageable=PageRequest*/
 		return repository.findByCommodityid(commodityid);
	}
	

}
