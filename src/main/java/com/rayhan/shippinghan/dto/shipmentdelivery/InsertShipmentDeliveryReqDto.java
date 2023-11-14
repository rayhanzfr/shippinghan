package com.rayhan.shippinghan.dto.shipmentdelivery;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertShipmentDeliveryReqDto {
	
	@NotNull(message = "shipment Id is mandatory")
	private Long shipmentId;
	@NotBlank(message = "receiverName must be filled")
	private String receiverName;
	
	
	public Long getShipmentId() {
		return shipmentId;
	}
	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
}
