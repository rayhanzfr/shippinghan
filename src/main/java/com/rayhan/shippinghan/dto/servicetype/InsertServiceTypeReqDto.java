package com.rayhan.shippinghan.dto.servicetype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertServiceTypeReqDto {
	
	@NotBlank(message = "Code is mandatory")
	private String code;
	
	@NotBlank(message = "Name must be filled")
	private String names;
	
	@NotNull(message = "createBy is mandatory")
	private Long createBy;
	
	@NotBlank(message = "isActive must be filled")
	private Boolean isActive;
	

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


}
