package com.rayhan.shippinghan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rayhan.shippinghan.dto.servicetype.DeleteServiceTypeResDto;
import com.rayhan.shippinghan.dto.servicetype.GetAllServiceTypeResDto;
import com.rayhan.shippinghan.dto.servicetype.GetByServiceTypeIdResDto;
import com.rayhan.shippinghan.dto.servicetype.InsertServiceTypeReqDto;
import com.rayhan.shippinghan.dto.servicetype.InsertServiceTypeResDto;
import com.rayhan.shippinghan.dto.servicetype.UpdateServiceTypeReqDto;
import com.rayhan.shippinghan.dto.servicetype.UpdateServiceTypeResDto;
import com.rayhan.shippinghan.service.ServiceTypeService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("service-types")
public class ServiceTypeController {

	@Autowired
	private ServiceTypeService serviceTypeService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllServiceTypeResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		GetAllServiceTypeResDto result = serviceTypeService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByServiceTypeIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		GetByServiceTypeIdResDto result = serviceTypeService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertServiceTypeResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertServiceTypeReqDto data) throws Exception {
		InsertServiceTypeResDto result  = serviceTypeService.insert(data);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateServiceTypeResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateServiceTypeReqDto data) throws Exception {
		UpdateServiceTypeResDto result  = serviceTypeService.update(data);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteServiceTypeResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws Exception {
		DeleteServiceTypeResDto result  = serviceTypeService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
