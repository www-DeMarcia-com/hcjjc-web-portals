package com.hsjjc.service;



import java.util.List;

import org.springframework.data.domain.Page;


import com.hsjjc.model.InfoNews;

public interface InfoNewsService {
	Page<InfoNews> queryAllInfoNews(int page,int size);
	InfoNews queryInfoByid(Long id);
	List<InfoNews> queryInfoByKinds(int page,int size,Long kindsid);
	List<InfoNews> queryAll();
	List<InfoNews> queryInfoByKinds(Long kindsid);
	List<InfoNews> queryNwesByKey(String key);
	List<InfoNews> queryNwesByKey(String key,Integer page,Integer size);
	InfoNews insertNews(InfoNews info);
/*	Integer queryRownumByid(Long id);
	InfoNews queryUp(Integer rownum);
	InfoNews queryDown(Integer rownum);*/
	Long findMax();
	Long findMin();
}
