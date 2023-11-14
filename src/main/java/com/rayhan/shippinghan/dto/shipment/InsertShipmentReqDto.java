package com.rayhan.shippinghan.dto.shipment;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class InsertShipmentReqDto {

	
	@NotNull(message = "id payment method must be filled")
	private Long paymentMethodId;
	
	@NotNull(message = "id service type must be filled")
	private Long serviceTypeId;
	
	@NotNull(message = "id branch must be filled")
	private Long branchId;
	
	@NotNull(message = "isActive must be filled")
	private Boolean isActive;
	
	@NotNull(message = "sendername must be filled")
	private String senderNames;
	
	@NotNull(message = "receiver name must be filled")
	private String receiverNames;
	
	@NotNull(message = "price must be filled")
	private BigDecimal price;
	
	@NotNull(message = "phone number must be filled")
	private String phoneNumber;
	
	@NotNull(message = "address must be filled")
	private String address;


	public Long getPaymentMethodId() {
		return paymentMethodId;
	}

	public void setPaymentMethodId(Long paymentMethodId) {
		this.paymentMethodId = paymentMethodId;
	}

	public Long getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Long serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}


	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getSenderNames() {
		return senderNames;
	}

	public void setSenderNames(String senderNames) {
		this.senderNames = senderNames;
	}

	public String getReceiverNames() {
		return receiverNames;
	}

	public void setReceiverNames(String receiverNames) {
		this.receiverNames = receiverNames;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
