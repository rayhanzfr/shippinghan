package com.rayhan.shippinghan.dto.servicetype;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UpdateServiceTypeReqDto {
	private Long id;
	@NotBlank(message = "Name must be filled")
	private String names;

	@NotNull(message = "updateBy is mandatory")
	private Long updateBy;

	@NotBlank(message = "isActive must be filled")
	private Boolean isActive;

	@NotNull(message = "version is be filled")
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
