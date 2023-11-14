package com.rayhan.shippinghan.dto.branch;

import java.time.LocalDateTime;

public class GetBranchDataDto {
	private Long id;
    private Long createdBy;
    private LocalDateTime createdDate;
    private Long updateBy;
    private LocalDateTime updateDate;
    private Boolean isActive;
    private Integer version;
    private Long cityId;
    private String code;
    private String cityNames;
    private String names;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
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
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	public Long getCityId() {
		return cityId;
	}
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
	public String getCityNames() {
		return cityNames;
	}
	public void setCityNames(String cityNames) {
		this.cityNames = cityNames;
	}
}
