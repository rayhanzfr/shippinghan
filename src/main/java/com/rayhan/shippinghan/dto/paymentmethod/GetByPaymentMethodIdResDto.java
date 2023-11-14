package com.rayhan.shippinghan.dto.paymentmethod;

public class GetByPaymentMethodIdResDto {
	private GetPaymentMethodDataDto data;
    private String message;
	
    public GetPaymentMethodDataDto getData() {
		return data;
	}
	public void setData(GetPaymentMethodDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
