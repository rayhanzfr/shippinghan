package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Roles extends BaseModel {
	@Column(name = "role_code", length = 10, unique = true, nullable = false)
	private String roleCode;

	private String names;

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
}
