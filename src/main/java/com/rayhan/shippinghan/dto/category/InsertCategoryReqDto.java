package com.rayhan.shippinghan.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertCategoryReqDto {

	@NotBlank(message = "code is mandatory")
	@Size(max = 10,message="Max Code name is 10 ")
	private String code;

	@NotBlank(message = "name must be filled")
	@Size(max = 30,message="Max Code name is 30 ")
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
