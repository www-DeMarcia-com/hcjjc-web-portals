package com.bootdo.system.controller;

import java.util.Collection;
import java.util.List;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.druid.pool.GetConnectionTimeoutException;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.UserOnline;
import com.bootdo.system.service.SessionService;

@RequestMapping("/sys/online")
@Controller
public class SessionController extends BaseController{
	@Autowired
	SessionService sessionService;

	@GetMapping()
	public String online() {
		return "system/online/online";
	}

	@ResponseBody
	@RequestMapping("/list")
	public List<UserOnline> list() {
		return sessionService.list();
	}

	@ResponseBody
	@RequestMapping("/forceLogout/{sessionId}")
	public R forceLogout(@PathVariable("sessionId") String sessionId, RedirectAttributes redirectAttributes) {
		try {
			int j=sessionService.forceLogout(sessionId,getUserId());
			if(j==1) {
				return R.error(1,"不允许强制退出超级管理员");
			}else if(j==2){
				return R.error(1,"不允许强制退出自己");
			}else {
				return R.ok();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return R.error();
		}
	}

	@ResponseBody
	@RequestMapping("/sessionList")
	public Collection<Session> sessionList() {
		return sessionService.sessionList();
	}
}
