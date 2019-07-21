package com.hsjjc.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.CommodityKinds;

public interface CommodityKindsService {
	public List<CommodityKinds> findAllCommodityKinds();
	
}
