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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rayhan.shippinghan.dto.shipment.GetAllShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.GetByShipmentIdResDto;
import com.rayhan.shippinghan.dto.shipment.InsertFullShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.InsertFullShipmentResDto;
import com.rayhan.shippinghan.dto.shipment.UpdateStatusShipmentReqDto;
import com.rayhan.shippinghan.dto.shipment.UpdateStatusShipmentResDto;
import com.rayhan.shippinghan.service.ShipmentService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "shipments")
public class ShipmentController {
	@Autowired
	private ShipmentService shipmentService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllShipmentResDto.class)))
	@GetMapping("/all")
	@ResponseBody
	public ResponseEntity<?> getAll(@RequestParam(required = false) Long branchId, String statusCode) throws Exception {
		GetAllShipmentResDto listRole = shipmentService.getAll(branchId, statusCode);
		return new ResponseEntity<>(listRole, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByShipmentIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		GetByShipmentIdResDto shipment = shipmentService.getById(id);
		return new ResponseEntity<>(shipment, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertFullShipmentResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertFullShipmentReqDto data) throws Exception {
		InsertFullShipmentResDto result = shipmentService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateStatusShipmentResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateStatusShipmentReqDto data) throws Exception {
		UpdateStatusShipmentResDto result = shipmentService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
