package com.rayhan.shippinghan.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Shipment extends BaseModel {
	@ManyToOne
	@JoinColumn(name = "id_status", nullable = false)
	private Status status;

	@ManyToOne
	@JoinColumn(name = "id_payment", nullable = false)
	private PaymentMethod paymentMethod;

	@ManyToOne
	@JoinColumn(name = "id_service_type", nullable = false)
	private ServiceType serviceType;

	@ManyToOne
	@JoinColumn(name = "receiver_destination", nullable = false)
	private Branch receiverDestination;

	@Column(name = "shipment_number", unique = true, nullable = false)
	private String shipmentNumber;

	@Column(name = "sender_name")
	private String senderName;

	@Column(name = "receiver_name")
	private String receiverName;

	@Column(name = "shipping_date", unique = true, nullable = false)
	private LocalDateTime shippingDate;

	private BigDecimal price;

	@Column(name = "phone_receiver")
	private String phoneNumber;

	private String address;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public ServiceType getServiceType() {
		return serviceType;
	}

	public void setServiceType(ServiceType serviceType) {
		this.serviceType = serviceType;
	}

	public Branch getReceiverDestination() {
		return receiverDestination;
	}

	public void setReceiverDestination(Branch receiverDestination) {
		this.receiverDestination = receiverDestination;
	}

	public String getShipmentNumber() {
		return shipmentNumber;
	}

	public void setShipmentNumber(String shipmentNumber) {
		this.shipmentNumber = shipmentNumber;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public LocalDateTime getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(LocalDateTime shippingDate) {
		this.shippingDate = shippingDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
