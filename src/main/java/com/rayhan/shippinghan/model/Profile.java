package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Profile extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "id_branch", nullable = false)
	private Branch branch;
	@Column(name = "profile_code", length = 30, unique = true, nullable = false)
	private String profileCode;

	@Column(name = "firstnames", length = 30, nullable = false)
	private String firstNames;

	@Column(name = "lastnames", length = 30)
	private String lastNames;

	@Column(name = "phonenumber", length = 13)
	private String phoneNumber;

	@ManyToOne
	@JoinColumn(name = "id_user", nullable = true)
	private Users users;

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getProfileCode() {
		return profileCode;
	}

	public void setProfileCode(String profileCode) {
		this.profileCode = profileCode;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
