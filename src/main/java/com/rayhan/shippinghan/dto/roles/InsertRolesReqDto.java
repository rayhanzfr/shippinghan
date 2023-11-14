package com.rayhan.shippinghan.dto.roles;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertRolesReqDto {
	@NotBlank(message="Code name required")
	@Size(max = 20,message="Max Code name is 20 ")
	private String code;
	
	private String names;
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	private Integer version;

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
