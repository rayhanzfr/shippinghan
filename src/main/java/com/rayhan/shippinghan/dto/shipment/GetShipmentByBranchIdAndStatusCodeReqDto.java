package com.rayhan.shippinghan.dto.shipment;

public class GetShipmentByBranchIdAndStatusCodeReqDto {
	private Long branchId;
	private String statusCode;

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatudId(String statusCode) {
		this.statusCode = statusCode;
	}

}
