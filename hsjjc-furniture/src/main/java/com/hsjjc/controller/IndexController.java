package com.hsjjc.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hsjjc.model.Commodity;
import com.hsjjc.model.InfoNews;
import com.hsjjc.service.CommodityKindsService;
import com.hsjjc.service.CommodityService;
import com.hsjjc.service.CompanyService;
import com.hsjjc.service.InfoNewsKindsService;
import com.hsjjc.service.InfoNewsService;

@Controller
public class IndexController {
	/*@Autowired
	CompanyService companyService;
	@Value("${Company.id}")
	Long companyid;*/
	@Autowired
	CommodityKindsService commoditykinds;
	@Autowired
	InfoNewsKindsService infonewskinds;
	@Autowired
	InfoNewsService infonewsService;
	@Value("${page.size}")
	Integer size;
	@Autowired
	CompanyService companyService;
	@Value("${Company.id}")
	Long companyid;
	@Autowired
	CommodityService commodityService;
	@Value("${nginx.port}")
	Integer nginxPort;
	@Value("${nginx.ip}")
	String nginxIp;
	
	@RequestMapping("/index")
	public String index(Model model) {
		model.addAttribute("commoditykinds",commoditykinds.findAllCommodityKinds());
		model.addAttribute("nginxPort", nginxPort);
		model.addAttribute("nginxIp", nginxIp);
		model.addAttribute("commoditys", commodityService.findByIsHotAll(0,9));
		List<InfoNews>news =infonewsService.queryAllInfoNews(0, 8).getContent();
		model.addAttribute("news", news);	
		return "index";
	}
	@RequestMapping("/articlelist")
	public String infonews(Model model) {
		model.addAttribute("infonewskinds", infonewskinds.queryAllInfoNewsKinds());
		model.addAttribute("newsmore", infonewsService.queryAllInfoNews(0, size));
		return "articlelist";
	}
	@RequestMapping("/newslistmore")
	public String infonewsmore(Model model,@RequestParam("page")Integer page) {
		//总条数
		int totle= infonewsService.queryAll().size();
		BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(size+""),0,RoundingMode.CEILING);
		model.addAttribute("totlepage", bg.intValue());
		model.addAttribute("page", page+1);
		return "newslistmore";
	}
	
	@RequestMapping("/articlelistfk")
	public String infonewsforkey(Model model,@RequestParam("key")String key,@RequestParam("page")Integer page) {
		model.addAttribute("key",key);
		int totle= infonewsService.queryNwesByKey(key).size();
		BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(size+""),0,RoundingMode.CEILING);
		model.addAttribute("totlepage", bg.intValue());
		model.addAttribute("page", page+1);
		return "articlelistfk";
	}
	
	@RequestMapping("/aboutus")
	public String aboutus(Model model) {
		return "aboutus";
	}
	@RequestMapping("/articlecontent")
	public String articlecontent(Model model,@RequestParam("article") Long article) {
		model.addAttribute("infonewskinds", infonewskinds.queryAllInfoNewsKinds());
		InfoNews infoup=null;
		InfoNews infodown=null;
		Long max=infonewsService.findMax();
		Long min=infonewsService.findMin();
		if(article>max) {
			article=max;
		}else if(article<min){
			article=min;
		}
		if(max!=article) {
			infoup=infonewsService.queryInfoByid(article+1);
		}
		if(min!=article) {
			infodown=infonewsService.queryInfoByid(article-1);
		}
		InfoNews info=infonewsService.queryInfoByid(article);
		/*if(article>max) {
			info=infonewsService.queryInfoByid(max);
		}else if(article<min) {
			info=infonewsService.queryInfoByid(min);
		}else{*/
			//info=infonewsService.queryInfoByid(article);
		/*}*/
		model.addAttribute("article", info);
		model.addAttribute("articleup", infoup);
		model.addAttribute("articledown", infodown);
		return "articlecontent";
	}
	
	@RequestMapping("/contact")
	public String contact(Model model) {
		return "contact";
	}
	@RequestMapping("/map")
	public String map(Model model) {
		return "map";
	}
	
}
