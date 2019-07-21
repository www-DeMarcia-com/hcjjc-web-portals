package com.bootdo.system.controller;

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
import com.bootdo.system.domain.InfonewsKindsDO;
import com.bootdo.system.service.InfonewsKindsService;

@RequestMapping("/web/infonewskinds")
@Controller
public class InfonewsKindsController {
	private String prefix="system/infonewskinds" ;
	@Autowired
	InfonewsKindsService kindsService;
	@GetMapping()
	@RequiresPermissions("web:infonewskinds:infonewskinds")
	String infonewskinds() {
		return prefix+"/infonewskinds";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:infonewskinds:infonewskinds")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<InfonewsKindsDO> kindsList = kindsService.list(query);
		int total = kindsService.count(query);
		PageUtils pageUtils = new PageUtils(kindsList, total);
		return pageUtils;
	}
	

	@GetMapping("/add")
	@RequiresPermissions("web:infonewskinds:add")
	String add() {
		return prefix+"/add";
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:infonewskinds:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		InfonewsKindsDO kinds = kindsService.get(id);
		if(kinds==null) {
			kinds=new InfonewsKindsDO();
		}
		model.addAttribute("kinds", kinds);
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@Log("添加新闻类型")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:infonewskinds:add")
	public R save(InfonewsKindsDO kinds) {
		/*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}*/
		if (kindsService.save(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@Log("修改新闻类型")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:infonewskinds:edit")
	public R update(InfonewsKindsDO kinds) {
	/*System.out.println(kinds.getId());
	System.out.println(kinds.getName());*/
		if (kindsService.update(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("删除新闻类型")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("web:infonewskinds:remove")
	public R remove(Long id) {
		/*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}*/
		if (kindsService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("批量删除新闻类型")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:infonewskinds:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		/*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}*/
		kindsService.batchRemove(ids);
		return R.ok();
	}
	
}
