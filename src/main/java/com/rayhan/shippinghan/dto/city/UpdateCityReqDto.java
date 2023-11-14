package com.rayhan.shippinghan.dto.city;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateCityReqDto {

	@NotNull(message = "Id is mandatory")
	private Long id;

	@NotBlank(message = "name mus be filled")
	private String names;

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
