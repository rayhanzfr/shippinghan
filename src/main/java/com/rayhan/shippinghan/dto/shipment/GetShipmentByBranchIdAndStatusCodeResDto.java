package com.rayhan.shippinghan.dto.shipment;

import java.util.List;

public class GetShipmentByBranchIdAndStatusCodeResDto {
	private List<GetShipmentDataDto> data;

	public List<GetShipmentDataDto> getData() {
		return data;
	}

	public void setData(List<GetShipmentDataDto> data) {
		this.data = data;
	}
}
