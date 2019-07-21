package com.bootdo.oa.controller;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.oa.domain.NotifyDO;
import com.bootdo.oa.domain.NotifyRecordDO;
import com.bootdo.oa.domain.Response;
import com.bootdo.oa.service.NotifyRecordService;
import com.bootdo.oa.service.NotifyService;
import com.bootdo.system.service.UserService;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 通知通告
 *
 * @author chglee
 * @email 1992lcg@163.com
 * @date 2017-10-05 17:11:16
 */

@Controller
@RequestMapping("/oa/notify")
public class NotifyController extends BaseController {
	@Autowired
	private NotifyService notifyService;
	@Autowired
	SimpMessagingTemplate template;
	@Autowired
	NotifyRecordService nrservice;
	@Autowired
	UserService user;
	
	@GetMapping()
	@RequiresPermissions("oa:notify:notify")
	String oaNotify() {
		return "oa/notify/notify";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("oa:notify:notify")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<NotifyDO> notifyList = notifyService.list(query);
		int total = notifyService.count(query);
		PageUtils pageUtils = new PageUtils(notifyList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("oa:notify:add")
	String add() {
		return "oa/notify/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("oa:notify:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		NotifyDO notify = notifyService.get(id);
		model.addAttribute("notify", notify);		
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("notifyId", notify.getId());
		List<NotifyRecordDO> list=nrservice.list(map);
		List<String>l=new ArrayList<String>();
		if(list!=null&&list.size()>0) {
			for(NotifyRecordDO nr :list) {
				l.add(nr.getUserId()+"【"+user.get(nr.getUserId()).getName()+"】");
			}
		}
		String str="";
		if(l.size()>0) {
			str=Arrays.toString(l.toArray()).replace("[", "").replace("]", "");
		}
		model.addAttribute("user",str );
		return "oa/notify/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("oa:notify:add")
	public R save(NotifyDO notify) {
		notify.setCreateBy(getUserId());
		if (notifyService.save(notify) > 0) {
			if("1".equals(notify.getStatus())) {
				Map <String,Object>map=new HashMap<String,Object>();
				map.put("notifyId", notify.getId());
				List<NotifyRecordDO>list=nrservice.list(map);
				if(list!=null&&list.size()>0) {
					for(NotifyRecordDO nr:list) {
						template.convertAndSend("/user/"+nr.getUserId()+"/message",new Response(notify.getTitle()));
					}

				}
			}
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("oa:notify:edit")
	public R update(NotifyDO notify) {
		if("1".equals(notify.getStatus())) {
			Map <String,Object>map=new HashMap<String,Object>();
			map.put("notifyId", notify.getId());
			List<NotifyRecordDO>list=nrservice.list(map);
			if(list!=null&&list.size()>0) {
				for(NotifyRecordDO nr:list) {
					template.convertAndSend("/user/"+nr.getUserId()+"/message",new Response(notify.getTitle()));
				}

			}
		}
		notifyService.update(notify);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("oa:notify:remove")
	public R remove(Long id) {
		if (notifyService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("oa:notify:batchRemove")
	public R remove(@RequestParam("ids[]") Long[] ids) {
		notifyService.batchRemove(ids);
		return R.ok();
	}

	@ResponseBody
	@GetMapping("/message")
	PageUtils message() {
		Map<String, Object> params = new HashMap<>(16);
		params.put("offset", 0);
		params.put("limit", 5);
		//params.put("isRead",0);
		Query query = new Query(params);
		query.put("userId", getUserId());
		query.put("isRead", 0);
		return notifyService.selfList(query);
	}

	@GetMapping("/selfNotify")
	String selefNotify() {
		return "oa/notify/selfNotify";
	}

	@ResponseBody
	@GetMapping("/selfList")
	PageUtils selfList(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		query.put("userId", getUserId());
		return notifyService.selfList(query);
	}

	@GetMapping("/read/{id}")
	@RequiresPermissions("oa:notify:edit")
	String read(@PathVariable("id") Long id, Model model) {
		NotifyDO notify = notifyService.get(id);
		/*Map<String,Object> map=new HashMap<String,Object>();
		map.put("notifyId", id);
		map.put("userId", getUserId());
		List<NotifyRecordDO>list=nrservice.list(map);
		for(NotifyRecordDO nr:list) {
			nr.setIsRead(1);
			nrservice.update(nr);
		}*/
		model.addAttribute("notify", notify);
		return "oa/notify/read";
	}
	@GetMapping("/self/read/{id}")
	String selfread(@PathVariable("id") Long id, Model model) {
		NotifyDO notify = notifyService.get(id);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("notifyId", id);
		map.put("userId", getUserId());
		List<NotifyRecordDO>list=nrservice.list(map);
		for(NotifyRecordDO nr:list) {
			if(nr.getIsRead()==0) {
			nr.setIsRead(1);
			nrservice.update(nr);
			}
		}
		model.addAttribute("notify", notify);
		return "oa/notify/read";
	}
}
