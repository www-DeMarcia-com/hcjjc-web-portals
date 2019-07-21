package com.hsjjc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.InfoNews;

public interface InfoNewsRepository extends JpaRepository<InfoNews, Long>{
//	SimpleJpaRepository<T, Serializable>
	/*List<People> findByAdress(String adress);
	
	People findByNameAndAdress(String name,String adress);
	
	@Query("select p from People p where p.name like :name and Adress=:adress")
	People findAllPeopleWithNameQuery (@Param("name") String name,@Param("adress") String adress);
	
	List<People> findByNameOrAdress (String name,String adress);*/
	
	@Query("select p from InfoNews p where Infonewskindsid=:infonewskindsid order by time desc")
	List<InfoNews> findBykindsid(@Param("infonewskindsid")Long infonewskindsid,Pageable pageable);
	
	@Query("select p from InfoNews p where Infonewskindsid=:infonewskindsid order by time desc")
	List<InfoNews> findBykindsid(@Param("infonewskindsid")Long infonewskindsid);
	
	@Query("select p from InfoNews p where title like %:key% or tags like %:key% or content like %:key% order by time desc")
	List<InfoNews> findByKeyPage(@Param("key")String key,Pageable pageable);
	
	@Query("select p from InfoNews p where title like %:key% or tags like  %:key% or content like %:key%  order by time desc")
	List<InfoNews> findByKey(@Param("key")String key);
	
	@Query("select max(id) from InfoNews p ")
	Long findMax();
	
	@Query("select min(id) from InfoNews p ")
	Long findMin();
	
	
}

