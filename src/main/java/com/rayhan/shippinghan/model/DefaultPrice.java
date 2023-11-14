package com.rayhan.shippinghan.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="default_price")
public class DefaultPrice extends BaseModel{
	@ManyToOne
	@JoinColumn(name = "id_branches", nullable=false)
	private Branch branch;
	
	@ManyToOne
	@JoinColumn(name = "id_service_type",nullable=false)
	private ServiceType serviceType;
	
	@Column(name="default_price_code" ,length=20 ,unique=true ,nullable=false)
	private String defaultPriceCode;
	
	private BigDecimal price;
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public ServiceType getServiceType() {
		return serviceType;
	}
	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}
	public String getDefaultPriceCode() {
		return defaultPriceCode;
	}
	public void setDefaultPriceCode(String defaultPriceCode) {
		this.defaultPriceCode = defaultPriceCode;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
