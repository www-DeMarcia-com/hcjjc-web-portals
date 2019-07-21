package com.hsjjc.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hsjjc.model.Commodity;
import com.hsjjc.service.CommodityKindsService;
import com.hsjjc.service.CommodityService;
import com.hsjjc.service.PictureService;

@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	CommodityKindsService commoditykinds;
	@Autowired
	CommodityService commodityService;
	
	@Autowired
	PictureService pictureService;
	
	@Value("${productpage.size}")
	Integer productsize;
	
	@Value("${nginx.port}")
	Integer nginxport;
	
	@Value("${nginx.ip}")
	String nginxurl;
	
	@RequestMapping("/productlist")
	public String productlist(Model model) {
		model.addAttribute("commoditykinds",commoditykinds.findAllCommodityKinds());
		int totle=commodityService.findAll().size();
		BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(productsize+""),0,RoundingMode.CEILING);
		model.addAttribute("totlepage", bg.intValue());
		model.addAttribute("page", 1);
		model.addAttribute("nginxport", nginxport);
		model.addAttribute("nginxurl", nginxurl);
		return "productlist";
	}
	
	@RequestMapping("/productdetails")
	public String productdetails(Model model,@RequestParam("productid")Long productid) {
		Map<String,Object> map=new HashMap<String,Object>();
		Commodity comm=commodityService.findOneById(productid);
		if(comm!=null) {
			String nginx=nginxurl+":"+nginxport;
			map.put("nginx", nginx);
			map.put("pic", pictureService.picListByCommodityid(productid));
			String specname[]=comm.getSpecname()==null?null:comm.getSpecname().split("@@");
			String specvalue[]=comm.getSpecvalue()==null?null:comm.getSpecvalue().split("@@");
			map.put("specname", specname);
			map.put("specvalue", specvalue);
			Date date=comm.getCreatetime();
			long l=date.getTime()+30*3600*24;
			int isflesh=l-new Date().getTime()>=0?0:1;
			map.put("isflesh", isflesh);	
		}
		map.put("commodity", comm);
		model.addAttribute("commodityMap", map);
		return "productdetail";
	}
	
	@RequestMapping("/indexlist")
	public String indexlist(Model model,@RequestParam("kindsid")Long kindsid) {
		model.addAttribute("kindsid", kindsid);
		model.addAttribute("commoditykinds",commoditykinds.findAllCommodityKinds());
		int totle=commodityService.findAll().size();
		BigDecimal bg=new BigDecimal(totle+"").divide(new BigDecimal(productsize+""),0,RoundingMode.CEILING);
		model.addAttribute("totlepage", bg.intValue());
		model.addAttribute("page", 1);
		model.addAttribute("nginxport", nginxport);
		model.addAttribute("nginxurl", nginxurl);
		return "indexlist";
	}
}
