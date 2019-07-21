package com.bootdo.system.controller;

import com.bootdo.common.annotation.Log;
import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.Tree;
import com.bootdo.common.utils.MD5Utils;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.system.domain.DeptDO;
import com.bootdo.system.domain.RoleDO;
import com.bootdo.system.domain.UserDO;
import com.bootdo.system.domain.UserRoleDO;
import com.bootdo.system.service.RoleService;
import com.bootdo.system.service.UserRoleService;
import com.bootdo.system.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/sys/user")
@Controller
public class UserController extends BaseController {
	private String prefix="system/user"  ;
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserRoleService userRole;
	@RequiresPermissions("sys:user:user")
	@GetMapping("")
	String user(Model model) {
		return prefix + "/user";
	}

	@GetMapping("/list")
	@ResponseBody
	PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<UserDO> sysUserList = userService.list(query);
		int total = userService.count(query);
		PageUtils pageUtil = new PageUtils(sysUserList, total);
		return pageUtil;
	}

	@RequiresPermissions("sys:user:add")
	@Log("添加用户")
	@GetMapping("/add")
	String add(Model model) {
		List<RoleDO> roles = roleService.list();
		model.addAttribute("roles", roles);
		return prefix + "/add";
	}

	@RequiresPermissions("sys:user:edit")
	@Log("编辑用户")
	@GetMapping("/edit/{id}")
	String edit(Model model, @PathVariable("id") Long id) {
		UserDO userDO = userService.get(id);
		model.addAttribute("user", userDO);
		/*List<RoleDO> roles = roleService.list(id);
		model.addAttribute("roles", roles);*/
		List<RoleDO> roles=roleService.list();
		Map<String,Object>map=new HashMap<String,Object>();
		map.put("userId", id);
		List<UserRoleDO>userrole=userRole.list(map);
		System.out.println(userrole.size());
		model.addAttribute("roles", roles);
		if(userrole==null||userrole.size()<=0||userrole.get(0)==null) {
			model.addAttribute("userrole","");
		}else {
			model.addAttribute("userrole",userrole.get(0).getRoleId());
		}
		//model.addAttribute("userrole", userrole==null?"":userrole.get(0)==null?"":userrole.get(0).getRoleId());
		return prefix+"/edit";
	}

	@RequiresPermissions("sys:user:add")
	@Log("保存用户")
	@PostMapping("/save")
	@ResponseBody
	R save(UserDO user,@RequestParam("role")Long roleid) {
		
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		if (userService.save(user) > 0) {
			UserRoleDO userrole=new UserRoleDO();
			userrole.setRoleId(roleid);
			userrole.setUserId(user.getUserId());
			userRole.save(userrole);
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:edit")
	@Log("更新用户")
	@PostMapping("/update")
	@ResponseBody
	R update(UserDO user) {
		/*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}*/
		if (userService.update(user) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:remove")
	@Log("删除用户")
	@PostMapping("/remove")
	@ResponseBody
	R remove(Long id) {

		if(id==getUserId()) {
			return R.error(1,"自己不能删除自己");
		}else if(1l==id) {
			return R.error(1,"不能删除超级管理员");
		}
		//删除角色权限
		Map<String ,Object> map=new HashMap<String,Object>();
		map.put("userId",id);
		List<UserRoleDO> list=userRole.list(map);
		if(list!=null&&list.size()>0) {
			for(UserRoleDO userrole:list) {
				if(userrole!=null) {
				userRole.delete(userrole.getId());
				}
			}
		}
		if (userService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	@RequiresPermissions("sys:user:batchRemove")
	@Log("批量删除用户")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] userIds) {
		/*if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}*/
		for(Long id:userIds) {
			if(id==getUserId()) {
				return R.error(1,"自己不能删除自己");
			}else if(1l==id) {
				return R.error(1,"不能删除超级管理员");
			}
		}
		for(Long id:userIds) {
			//删除角色权限
			Map<String ,Object> map=new HashMap<String,Object>();
			map.put("userId",id);
			List<UserRoleDO> list=userRole.list(map);
			if(list!=null&&list.size()>0) {
				for(UserRoleDO userrole:list) {
					if(userrole!=null) {
					userRole.delete(userrole.getId());
					}
				}
			}
		}
		int r = userService.batchremove(userIds);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}

	@PostMapping("/exit")
	@ResponseBody
	boolean exit(@RequestParam Map<String, Object> params) {
		// 存在，不通过，false
		return !userService.exit(params);
	}

	@RequiresPermissions("sys:user:resetPwd")
	@GetMapping("/resetPwd/{id}")
	String resetPwd(@PathVariable("id") Long userId, Model model) {
		UserDO userDO = new UserDO();
		userDO.setUserId(userId);
		model.addAttribute("user", userDO);
		return prefix + "/reset_pwd";
	}

	@Log("更改用户密码")
	@PostMapping("/resetPwd")
	@RequiresPermissions("sys:user:resetPwd")
	@ResponseBody
	R resetPwd(UserDO user) {
		if(user.getUserId()==1l&&1l!=getUserId()) {
			return R.error();
		}
		user.setPassword(MD5Utils.encrypt(userService.get(user.getUserId()).getUsername(), user.getPassword()));
		if (userService.resetPwd(user) > 0) {
			return R.ok();
		}
		return R.error();
	}
	
	
	@GetMapping("/selfResetPwd")
	String resetPwd() {
		return prefix + "/reset_pwd_self";
	}
	
	@Log("更改用户密码")
	@PostMapping("/selfResetPwd/update")
	@ResponseBody
	R selfResetPwd(@RequestParam("new_password")String password,@RequestParam("password")String password2) {
		UserDO user=getUser();
		String pwd=MD5Utils.encrypt(userService.get(user.getUserId()).getUsername(), password2);
		if(user.getPassword().equals(pwd)) {
			user.setPassword(MD5Utils.encrypt(userService.get(user.getUserId()).getUsername(), password));
			if (userService.resetPwd(user) > 0) {
				return R.ok();
			}else {
				return R.error();
			}
		}else {
			return R.error(1,"原密码错误");
		}
		
	}
	
	@GetMapping("/tree")
	@ResponseBody
	public Tree<DeptDO> tree() {
		Tree<DeptDO> tree = new Tree<DeptDO>();
		tree = userService.getTree();
		return tree;
	}

	@GetMapping("/treeView")
	String treeView() {
		return  prefix + "/userTree";
	}

}
