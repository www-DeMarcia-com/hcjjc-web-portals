package com.hsjjc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.Picture;

public interface PictureRepository extends JpaRepository<Picture, Long>{

	@Query("select p from Picture p where commodityid=:commodityid order by rank asc")
	List<Picture> findByCommodityid(@Param("commodityid")Long commodityid);
}

