package com.bootdo.system.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.InfoNewsDO;
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.service.InfonewsKindsService;
import com.bootdo.system.service.InfonewsService;

@RequestMapping("/web/infonews")
@Controller
public class InfonewsController {
	private String prefix="system/infonews" ;
	@Autowired
	InfonewsService kindsService;
	
	@Autowired
	InfonewsKindsService ikindsService;
	
	@GetMapping()
	@RequiresPermissions("web:infonews:infonews")
	public String infonewskinds(Model model) {
		return prefix+"/infonews";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:infonews:infonews")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<InfoNewsDO> kindsList = kindsService.list(query);
		int total = kindsService.count(query);
		PageUtils pageUtils = new PageUtils(kindsList, total);
		return pageUtils;
	}
	
	@ResponseBody
	@GetMapping("/commkindslist")
	@RequiresPermissions("web:infonews:infonews")
	public List<InfonewsKindsDO> commkindslist() {
		return ikindsService.list(new HashMap<String,Object>());
	}
	

	@GetMapping("/add")
	@RequiresPermissions("web:infonews:add")
	public String add(Model model) {
		model.addAttribute("infokinds", ikindsService.list(new HashMap<String,Object>()));
		return prefix+"/add";
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:infonews:edit")
	public String edit(@PathVariable("id") Long id, Model model) {
		InfoNewsDO kinds = kindsService.get(id);
		if(kinds==null) {
			kinds=new InfoNewsDO();
		}
		model.addAttribute("kinds", kinds);
		model.addAttribute("infokinds", ikindsService.list(new HashMap<String,Object>()));
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@Log("添加新闻")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:infonews:add")
	public R save(InfoNewsDO kinds) {
		String contentgg= kinds.getContentgg().length()>200? kinds.getContentgg().substring(0, 200): kinds.getContentgg();
		kinds.setContentgg(contentgg);
		if (kindsService.save(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@Log("修改新闻")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:infonews:edit")
	public R update(InfoNewsDO kinds) {
		String contentgg= kinds.getContentgg().length()>200? kinds.getContentgg().substring(0, 200): kinds.getContentgg();
		kinds.setContentgg(contentgg);
		if (kindsService.update(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("删除新闻")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("web:infonews:remove")
	public R remove(Long id) {
		if (kindsService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("批量删除新闻")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:infonews:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		kindsService.batchRemove(ids);
		return R.ok();
	}
	
}
