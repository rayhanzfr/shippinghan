package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class Category extends BaseModel{
	@Column(name="cat_code" ,length=10 ,unique=true ,nullable=false)
	private String catCode;
	@Column(length=30)
	private String names;
	public String getCatCode() {
		return catCode;
	}
	public void setCatCode(String catCode) {
		this.catCode = catCode;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
}
