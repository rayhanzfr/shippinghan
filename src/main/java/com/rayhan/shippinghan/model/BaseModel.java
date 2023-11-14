package com.rayhan.shippinghan.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

@MappedSuperclass
public class BaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="created_by", nullable=false)
	private Long createdBy;
	@Column(name="created_date" ,nullable=false)
	private LocalDateTime createdDate;
	@Column(name="update_by")
	private Long updateBy;
	@Column(name="update_date")
	private LocalDateTime updateDate;
	@Column(name="isactive",nullable=false)
	private Boolean isActive;
	@Version
	private Integer version;

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
	@PrePersist
	public void preCreate() {
		this.createdDate=LocalDateTime.now();
	}
	public Long getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(Long update_by) {
		this.updateBy = update_by;
	}
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
	@PreUpdate
	public void preUpdate() {
		this.updateDate=LocalDateTime.now();
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
