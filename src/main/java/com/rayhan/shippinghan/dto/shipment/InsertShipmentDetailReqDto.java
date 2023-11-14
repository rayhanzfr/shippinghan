package com.rayhan.shippinghan.dto.shipment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class InsertShipmentDetailReqDto {
	
	@NotNull(message = "Category id is mandatory")
	private Long categoryId;
	@NotNull(message = "isActive is mandatory")
	private Boolean isActive;
	@NotBlank(message = "itemName must be filled")
	private String itemNames;
	@NotNull(message = "weight must be filled")
	private Integer weight;
	@NotNull(message = "quantity must be filled")
	private Integer quantity;
	
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
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
	
	
}
