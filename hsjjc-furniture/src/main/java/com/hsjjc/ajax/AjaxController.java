package com.hsjjc.ajax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.InfoNews;
import com.hsjjc.service.CommodityService;
import com.hsjjc.service.InfoNewsKindsService;
import com.hsjjc.service.InfoNewsService;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
	@Value("${page.size}")
	Integer size;
	
	@Value("${productpage.size}")
	Integer productsize;
	
	@Value("${nginx.port}")
	Integer nginxPort;
	@Value("${nginx.ip}")
	String nginxIp;
	
	@Autowired
	InfoNewsService infonewsService;
	@Autowired
	CommodityService commodityService;
	
	@RequestMapping(value="/newspage",method=RequestMethod.POST)
	@ResponseBody
	public List<InfoNews> list(@RequestParam("page") Integer page){
		return infonewsService.queryAllInfoNews(page-1, size).getContent();
	}
	
	@RequestMapping(value="/querynewsbykindsId",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object>querynewsbykindsId(@RequestParam("page") Integer page,@RequestParam("kindsid") Long kindsid){
		Map<String,Object> map=new HashMap<String,Object>();		
		map.put("kindlist", infonewsService.queryInfoByKinds(page, size, kindsid));
		int totle=infonewsService.queryInfoByKinds(kindsid).size();
		BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(size+""),0,RoundingMode.CEILING);
		map.put("bg", bg.intValue());
		map.put("page", page+1);
		return map;
		
	}
	
	@RequestMapping(value="/newspageforkey",method=RequestMethod.POST)
	@ResponseBody
	public List<InfoNews> list(@RequestParam("page") Integer page,@RequestParam("key") String key){
		return infonewsService.queryNwesByKey(key, page-1, size);		
	}
	
	@RequestMapping(value="/productlist",method=RequestMethod.POST)
	@ResponseBody
	public List<Commodity> commodityList(@RequestParam("page") Integer page) {
		Pageable pageable=new PageRequest(page-1, productsize);
		return commodityService.findByIsHotAll(pageable);
	}
	
	@RequestMapping(value="/querycommoditybykindsid",method=RequestMethod.POST)
	@ResponseBody
	public Map <String ,Object> querycommoditybykindsid(@RequestParam("kindsid") Long kindsid,@RequestParam("page") Integer page) {
		Map <String ,Object>map=new HashMap<String ,Object>();
		map.put("list", commodityService.findByCommoditykindsid(kindsid, page-1, productsize));
		int totle=commodityService.findByCommoditykindsid(kindsid).size();
		BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(productsize+""),0,RoundingMode.CEILING);
		map.put("bg", bg.intValue());
		map.put("page", page);
		return map;
	}
	
	
	@RequestMapping(value="/queryproductfk",method=RequestMethod.POST)
	@ResponseBody
	public Map <String ,Object> queryproductfk(@RequestParam("keywords") String keywords,@RequestParam ("page")Integer page) {
		Map <String ,Object>map=new HashMap<String ,Object>();
		 keywords = keywords.trim();
		 List<Commodity>list=commodityService.findAllByFk(keywords,page-1,productsize);
		 if(list!=null&&list.size()>0) {
			 Integer totle=commodityService.findAllByFk(keywords).size();
			 BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(productsize+""),0,RoundingMode.CEILING);
			 map.put("bg", bg.intValue());
			 map.put("page", page);
			 map.put("list", list);
		 }
		return map;
	}
	
}
