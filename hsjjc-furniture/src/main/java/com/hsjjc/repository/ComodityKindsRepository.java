package com.hsjjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.CommodityKinds;

public interface ComodityKindsRepository extends JpaRepository<CommodityKinds, Long>{

	
}

