	package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "cities")
public class City extends BaseModel {

	@Column(name = "city_code", length = 10, unique = true, nullable = false)
	private String cityCode;

	@Column(length = 10, unique = true, nullable = false)
	private String names;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}
}
