package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Status extends BaseModel{
	@Column(name="status_code",length = 30,unique=true,nullable = false)
	private String statusCode;
	@Column(name="status_name",length = 30,nullable = false)
	private String statusName;
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
