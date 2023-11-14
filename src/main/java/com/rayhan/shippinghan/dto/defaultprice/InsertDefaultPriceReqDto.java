package com.rayhan.shippinghan.dto.defaultprice;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertDefaultPriceReqDto {
	
	@NotBlank(message = "code is mandatory")
	@Size(max = 20,message = "Code must be max 20 char")
	private String code;
	
	@NotNull(message = "price must be filled")
	private Integer price;
	
	@NotNull(message = "Id branch must be filled")
	private Long branchId;
	
	@NotNull(message = "Id service must be filled")
	private Long serviceTypeId;
	
	@NotNull(message = "createdBy must be filled")
	private Long createBy;
	
	@NotNull(message = "isActive is required")
	private Boolean isActive;
	
	private Integer version;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Long getBranchId() {
		return branchId;
	}
	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	public Long getServiceTypeId() {
		return serviceTypeId;
	}
	public void setServiceTypeId(Long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	public Long getCreateBy() {
		return createBy;
	}
	public void setCreateBy(Long createBy) {
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
