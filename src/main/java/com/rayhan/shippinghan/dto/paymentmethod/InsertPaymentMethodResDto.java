package com.rayhan.shippinghan.dto.paymentmethod;

import com.rayhan.shippinghan.dto.InsertResDataDto;

public class InsertPaymentMethodResDto {
	private InsertResDataDto data;
	private String message;
	public InsertResDataDto getData() {
		return data;
	}
	public void setData(InsertResDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
