package com.rayhan.shippinghan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayhan.shippinghan.dto.shipmentdelivery.GetAllShipmentDeliveryResDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.GetByShipmentDeliveryIdResDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.InsertShipmentDeliveryReqDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.InsertShipmentDeliveryResDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.UpdateStatusShipmentDeliveryReqDto;
import com.rayhan.shippinghan.dto.shipmentdelivery.UpdateStatusShipmentDeliveryResDto;
import com.rayhan.shippinghan.service.ShipmentDeliveryService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("shipment-deliveries")
public class ShipmentDeliveryController {
	@Autowired
	private ShipmentDeliveryService shipmentDeliveryService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllShipmentDeliveryResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		GetAllShipmentDeliveryResDto listDelivery = shipmentDeliveryService.getAll();
		return new ResponseEntity<>(listDelivery, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByShipmentDeliveryIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		GetByShipmentDeliveryIdResDto result = shipmentDeliveryService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertShipmentDeliveryResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertShipmentDeliveryReqDto data) throws Exception {
		InsertShipmentDeliveryResDto result = shipmentDeliveryService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateStatusShipmentDeliveryResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateStatusShipmentDeliveryReqDto data) throws Exception {
		UpdateStatusShipmentDeliveryResDto result = shipmentDeliveryService.update(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

}
