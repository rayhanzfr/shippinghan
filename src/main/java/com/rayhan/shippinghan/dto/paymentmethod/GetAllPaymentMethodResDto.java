package com.rayhan.shippinghan.dto.paymentmethod;

import java.util.List;

public class GetAllPaymentMethodResDto {
	private List<GetPaymentMethodDataDto> data;
    private String message;
	
    public List<GetPaymentMethodDataDto> getData() {
		return data;
	}
    public void setData(List<GetPaymentMethodDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
