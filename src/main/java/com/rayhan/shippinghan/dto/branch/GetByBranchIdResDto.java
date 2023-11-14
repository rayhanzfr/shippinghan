package com.rayhan.shippinghan.dto.branch;

public class GetByBranchIdResDto {
	private GetBranchDataDto data;
    private String message;
	
    public GetBranchDataDto getData() {
		return data;
	}
	public void setData(GetBranchDataDto data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
    
}
