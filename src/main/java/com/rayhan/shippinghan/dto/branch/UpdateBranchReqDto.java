package com.rayhan.shippinghan.dto.branch;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UpdateBranchReqDto {

	@NotNull(message = "Id is mandatory")
	private Long id;

	@NotBlank(message = "Name is mandatory")
	@Size(max = 30, message = "Name must be 50 character")
	private String names;

	@NotNull(message = "Id City is mandatory")
	private Long cityId;

	@NotNull(message = "updateBy must be filled")
	private Long updateBy;

	@NotNull(message = "isActive must be filled")
	private Boolean isActive;

	@NotNull(message = "version must be filled")
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

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

}
