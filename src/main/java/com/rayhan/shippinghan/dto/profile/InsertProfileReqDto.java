package com.rayhan.shippinghan.dto.profile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class InsertProfileReqDto {
	
	@NotNull(message = "user id is mandatory")
	private Long userId;
	
	@NotNull(message = "branch id is mandatory")
	private Long branchId;
	
	@NotBlank(message = "code is mandatory")
	@Size(max =30,message = "code max 30 Char")
	private String code;
	
	@NotBlank(message = "firstNames must be filled")
	@Size(max =30,message = "firstNames max 30 Char")
	private String firstNames;
	
	private String lastNames;
	
	private String phoneNumber;
	
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	
	private Integer version;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getFirstNames() {
		return firstNames;
	}

	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}

	public String getLastNames() {
		return lastNames;
	}

	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
