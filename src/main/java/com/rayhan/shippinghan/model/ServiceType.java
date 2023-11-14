package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="service_type")
public class ServiceType extends BaseModel {
	@Column(name = "service_code", length = 20, unique = true, nullable = false)
	private String serviceCode;

	@Column(name = "service_name", length = 30, nullable = false)
	private String serviceName;

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
}
