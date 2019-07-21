package com.hsjjc.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import com.hsjjc.model.Commodity;

public interface ComodityRepository extends JpaRepository<Commodity, Long>{
//	SimpleJpaRepository<T, Serializable>
	/*List<People> findByAdress(String adress);
	
	People findByNameAndAdress(String name,String adress);
	
	@Query("select p from People p where p.name like :name and Adress=:adress")
	People findAllPeopleWithNameQuery (@Param("name") String name,@Param("adress") String adress);
	
	List<People> findByNameOrAdress (String name,String adress);*/
	@Query("select p from Commodity p where id=:commodityid and isclose=0 order by createtime desc")
	Commodity findId(@Param("commodityid") Long commodityid);
	
	
	List<Commodity> findByName(String name);
	
	@Query("select p from Commodity p where ishot=1 and isclose=0 order by createtime desc")
	List<Commodity> findIshot();
	
	@Query("select p from Commodity p where (ishot!=1 and ispromotion=1) and isclose=0 order by createtime desc")
	List<Commodity> findByIspromotionNotIshot();
	@Query("select p from Commodity p where (ishot!=1 or ispromotion!=1) and isclose=0 order by createtime desc")
	List<Commodity> findByIsNotHot();
	
	@Query("select p from Commodity p where (ishot=1 or ispromotion=1) and isclose=0 order by ispromotion desc,ishot desc,createtime desc")
	List<Commodity> findIshotAll(Pageable pageable);
	
	@Query("select p from Commodity p where commoditykindsid=:kindsid and isclose=0 order by createtime desc")
	List<Commodity> findByCommokindsid(@Param("kindsid") Long kindsid,Pageable pageable);
	
	@Query("select p from Commodity p where commoditykindsid=:kindsid and isclose=0")
	List<Commodity> findByCommokindsidAll(@Param("kindsid") Long kindsid);
	
	@Query("select p from Commodity p where isclose=0 order by ispromotion desc,ishot desc,createtime desc")
	List<Commodity>findbyhot(Pageable pageable);

	@Query("select p from Commodity p where isclose=0 and name like %:keywords% order by ispromotion desc,ishot desc,createtime desc")
	List<Commodity> findAllByFk(@Param("keywords")String keywords, Pageable pageable);
	
	@Query("select p from Commodity p where isclose=0 and name like %:keywords% order by ispromotion desc,ishot desc,createtime desc")
	List<Commodity> findAllByFk(@Param("keywords")String keywords);
}

