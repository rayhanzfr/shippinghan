package com.rayhan.shippinghan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="payment_method")
public class PaymentMethod extends BaseModel{
	@Column(name="payment_code",length=30 ,unique=true ,nullable=false)
	private String paymentCode;
	
	@Column(name="payment_name",length=30 ,nullable=false)
	private String paymentName;
	public String getPaymentCode() {
		return paymentCode;
	}
	public void setPaymentCode(String paymentCode) {
		this.paymentCode = paymentCode;
	}
	public String getPaymentName() {
		return paymentName;
	}
	public void setPaymentName(String paymentName) {
		this.paymentName = paymentName;
	}
}
