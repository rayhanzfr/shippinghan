package com.rayhan.shippinghan.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shipment_delivery")
public class ShipmentDelivery extends BaseModel{
	@ManyToOne
	@JoinColumn(name="id_shipment",nullable=false)
	private Shipment shipment;
	@ManyToOne
	@JoinColumn(name="id_status",nullable=false)
	private Status status;
	@Column(name="receiver_name", length=30)
	private String receiverName;
	
	@Column(name="time_delivered")
	private LocalDateTime timeDelivered;
	
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public LocalDateTime getTimeDelivered() {
		return timeDelivered;
	}
	public void setTimeDelivered(LocalDateTime timeDelivered) {
		this.timeDelivered = timeDelivered;
	}
	
	
}
