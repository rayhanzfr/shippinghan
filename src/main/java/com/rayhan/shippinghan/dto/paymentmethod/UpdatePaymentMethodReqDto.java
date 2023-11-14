package com.rayhan.shippinghan.dto.paymentmethod;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdatePaymentMethodReqDto {
	
	@NotNull(message = "id is mandatory")
	private Long id;
	
	@NotBlank(message = "names must be filled")
	@Size(max = 30, message = "name must be max 30 char")
	private String names;
	
	@NotNull(message = "updateBy is mandatory")
	private Long updateBy;
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	private Integer version;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNames() {
		return names;
	}

	public void setNames(String names) {
		this.names = names;
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
