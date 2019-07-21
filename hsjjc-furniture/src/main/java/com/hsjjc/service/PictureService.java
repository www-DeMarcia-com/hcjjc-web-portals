package com.hsjjc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.Picture;

public interface PictureService {
	List<Picture> picListByCommodityid(Long commodityid);
}
