package com.bootdo.system.domain;

import java.io.Serializable;

public class RoleMenuDO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7406378141282537091L;
	private Long id;
	private Long  roleId;
	private Long menuId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	@Override
	public String toString() {
		return "RoleMenuDO{" +
				"id=" + id +
				", roleId=" + roleId +
				", menuId=" + menuId +
				'}';
	}
}
