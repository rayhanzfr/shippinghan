package com.rayhan.shippinghan.dto.shipmentdelivery;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UpdateStatusShipmentDeliveryReqDto {
	private Long id;
	@NotNull(message = "shipment Id is mandatory")
	private Long shipmentId;
	@NotNull(message = "status Id is mandatory")
	private Long statusId;
	@NotEmpty(message = "receiverName must be filled")
	private String receiverName;
	private Long updateBy;
	private Boolean IsActive;
	private Integer version;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getShipmentId() {
		return shipmentId;
	}
	
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	
	public Long getStatusId() {
		return statusId;
	}

	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getIsActive() {
		return IsActive;
	}

	public void setIsActive(Boolean isActive) {
		IsActive = isActive;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
}
