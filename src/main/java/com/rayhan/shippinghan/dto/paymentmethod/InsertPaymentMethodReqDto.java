package com.rayhan.shippinghan.dto.paymentmethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertPaymentMethodReqDto {
	@NotBlank(message = "Code is mandatory")
	@Size(max = 30,message = "Code must be max 30 char")
	private String code;
	
	@NotNull(message = "names must be filled")
	@Size(max = 30,message = "name must be max 30 char")
	private String names;
	
	@NotNull(message = "createBy is required")
	private Long createBy;

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
		return createBy;
	}
	public void setCreatedBy(Long createBy) {
		this.createBy = createBy;
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
