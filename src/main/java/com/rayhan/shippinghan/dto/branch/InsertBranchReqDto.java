package com.rayhan.shippinghan.dto.branch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertBranchReqDto {
	@NotBlank(message = "code is mandatory")
	@Size(max = 30,message="Max Code name is 30 ")
	private String code;
	
	@NotBlank(message = "name is mandatory")
	private String names;
	
	@NotNull(message = "id city is mandatory")
	private Long cityId;
	
	@NotNull(message = "createdBy is mandatory")
	private Long createBy;
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
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

}
