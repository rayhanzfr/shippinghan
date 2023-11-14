package com.rayhan.shippinghan.dto.city;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertCityReqDto {
	
	@NotNull(message = "Code is mandatory")
	@Size(max = 10,message = "code must be max 10 char")
	private String code;
	
	@NotBlank(message = "Name is mandatory")
	@Size(max = 10,message = "Name max 10 char")
	private String names;
	
	@NotNull(message = "createdBy is required")
	private Long createdBy;
	
	@NotNull(message = "isActive is required")
	private Boolean isActive;
	
	private Integer version;
	
	public void setCode(String code) {
		this.code = code;
	}
	public String getCode() {
		return code;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	
	
}
