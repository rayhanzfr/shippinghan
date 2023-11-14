package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "branches")
public class Branch extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "id_city")
	private City city;

	@Column(name = "branches_code", length = 30, unique = true, nullable = false)
	private String branchesCode;

	@Column(name = "branches_name", length = 50)
	private String branchesName;

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getBranchesCode() {
		return branchesCode;
	}

	public void setBranchesCode(String branchesCode) {
		this.branchesCode = branchesCode;
	}

	public String getBranchesName() {
		return branchesName;
	}

	public void setBranchesName(String branchesName) {
		this.branchesName = branchesName;
	}
}
