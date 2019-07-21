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
import com.bootdo.system.domain.ProducerDO;
import com.bootdo.system.service.ProducerService;

@RequestMapping("/web/producer")
@Controller
public class ProducerController {
	private String prefix="system/producer" ;
	@Autowired
	ProducerService kindsService;
	@GetMapping()
	@RequiresPermissions("web:producer:producer")
	String producer() {
		return prefix+"/producer";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("web:producer:producer")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<ProducerDO> kindsList = kindsService.list(query);
		int total = kindsService.count(query);
		PageUtils pageUtils = new PageUtils(kindsList, total);
		return pageUtils;
	}
	

	@GetMapping("/add")
	@RequiresPermissions("web:producer:add")
	String add() {
		return prefix+"/add";
	}
	
	@GetMapping("/edit/{id}")
	@RequiresPermissions("web:producer:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		ProducerDO kinds = kindsService.get(id);
		if(kinds==null) {
			kinds=new ProducerDO();
		}
		model.addAttribute("kinds", kinds);
		return  prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@Log("添加品牌")
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("web:producer:add")
	public R save(ProducerDO kinds) {
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
	@Log("修改品牌")
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("web:producer:edit")
	public R update(ProducerDO kinds) {
		if (kindsService.update(kinds) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("删除品牌")
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("web:producer:remove")
	public R remove(Long id) {
		if (kindsService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@Log("批量删除品牌")
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("web:producer:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
	
		kindsService.batchRemove(ids);
		return R.ok();
	}
	
}
