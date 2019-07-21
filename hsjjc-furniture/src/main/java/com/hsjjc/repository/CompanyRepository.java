package com.hsjjc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{
//	SimpleJpaRepository<T, Serializable>
	/*List<People> findByAdress(String adress);
	
	People findByNameAndAdress(String name,String adress);
	
	@Query("select p from People p where p.name like :name and Adress=:adress")
	People findAllPeopleWithNameQuery (@Param("name") String name,@Param("adress") String adress);
	
	List<People> findByNameOrAdress (String name,String adress);*/
	
	
}

