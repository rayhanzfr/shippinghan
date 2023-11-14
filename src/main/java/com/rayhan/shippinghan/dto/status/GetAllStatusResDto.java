package com.rayhan.shippinghan.dto.status;

import java.util.List;

public class GetAllStatusResDto {
	private List<GetStatusDataDto> data;
    private String message;
	
    public List<GetStatusDataDto> getData() {
		return data;
	}
    public void setData(List<GetStatusDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
