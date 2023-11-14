package com.rayhan.shippinghan.dto.status;

public class GetByStatusIdResDto {
	private GetStatusDataDto data;
    private String message;
	
    public GetStatusDataDto getData() {
		return data;
	}
	public void setData(GetStatusDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
