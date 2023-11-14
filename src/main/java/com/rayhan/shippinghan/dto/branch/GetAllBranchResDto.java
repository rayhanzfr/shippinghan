package com.rayhan.shippinghan.dto.branch;

import java.util.List;

public class GetAllBranchResDto {
	private List<GetBranchDataDto> data;
    private String message;
	
    public List<GetBranchDataDto> getData() {
		return data;
	}
    public void setData(List<GetBranchDataDto> data) {
		this.data = data;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
