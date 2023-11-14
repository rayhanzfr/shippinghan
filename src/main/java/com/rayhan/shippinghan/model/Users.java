package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Users extends BaseModel{
	@Column(name="usernames",length=16,unique=true,nullable=false)
	private String username;
	
	@Column(nullable=false)
	private String pass;
	
	private String email;
	@ManyToOne
	@JoinColumn(name="id_role",nullable=false)
	private Roles roles;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Roles getRoles() {
		return roles;
	}
	public void setRoles(Roles roles) {
		this.roles = roles;
	}
}
