package com.rayhan.shippinghan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rayhan.shippinghan.dto.shipment.GetAllShipmentDetailResDto;
import com.rayhan.shippinghan.dto.shipment.GetByShipmentDetailIdResDto;
import com.rayhan.shippinghan.dto.shipment.GetDetailByShipmentIdResDto;
import com.rayhan.shippinghan.service.ShipmentDetailService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "shipment-details")
public class ShipmentDetailController {
	@Autowired
	private ShipmentDetailService shipmentDetailService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllShipmentDetailResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		GetAllShipmentDetailResDto result = shipmentDetailService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByShipmentDetailIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		GetByShipmentDetailIdResDto result = shipmentDetailService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetDetailByShipmentIdResDto.class)))
	@GetMapping("/shipment-details")
	@ResponseBody
	public ResponseEntity<?> getByShipmentId(@RequestParam Long shipmentId) throws Exception {
		GetDetailByShipmentIdResDto result = shipmentDetailService.getByShipmentId(shipmentId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
