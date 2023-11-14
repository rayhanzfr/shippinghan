package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shipment_detail")
public class ShipmentDetail extends BaseModel{
	@ManyToOne
	@JoinColumn(name="id_shipment", nullable=false)
	private Shipment shipment;
	@ManyToOne
	@JoinColumn(name="id_cat", nullable=false)
	private Category category;
	@Column(name="item_name",length=30)
	private String itemName;
	private Integer weight;
	private Integer quantity;
	public Shipment getShipment() {
		return shipment;
	}
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
}
