package com.rayhan.shippinghan.dto.defaultprice;

import javax.validation.constraints.NotNull;

public class UpdateDefaultPriceReqDto {
	@NotNull(message = "id is mandatory")
	private Long id;
	
	@NotNull(message = "price must be filled")
	private Integer price;
	
	@NotNull(message = "Id branch must be filled")
	private Long branchId;
	
	@NotNull(message = "Id service must be filled")
	private Long serviceTypeId;
	
	@NotNull(message = "updateBy is required")
	private Long updateBy;

	@NotNull(message = "isActive is required")
	private Boolean isActive;
	
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
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
