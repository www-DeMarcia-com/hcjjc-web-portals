package com.hsjjc.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ErrorpageController {
	@RequestMapping("/400")
	public String error400(){
		return "forward:/index";
	}
	@RequestMapping("/500")
	public String error500(){
		return "forward:/index";
	}
	@RequestMapping("/404")
	public String error404(){
		return "forward:/index";
	}
}
