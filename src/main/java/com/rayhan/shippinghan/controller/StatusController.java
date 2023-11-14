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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rayhan.shippinghan.dto.status.DeleteStatusResDto;
import com.rayhan.shippinghan.dto.status.GetAllStatusResDto;
import com.rayhan.shippinghan.dto.status.GetByStatusIdResDto;
import com.rayhan.shippinghan.dto.status.InsertStatusReqDto;
import com.rayhan.shippinghan.dto.status.InsertStatusResDto;
import com.rayhan.shippinghan.dto.status.UpdateStatusReqDto;
import com.rayhan.shippinghan.dto.status.UpdateStatusResDto;
import com.rayhan.shippinghan.service.StatusService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("statuses")
public class StatusController {

	@Autowired
	private StatusService statusService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllStatusResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() {
		GetAllStatusResDto result = null;
		try {
			result = statusService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByStatusIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		GetByStatusIdResDto result = null;
		try {
			result = statusService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertStatusResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertStatusReqDto data) {
		InsertStatusResDto result = null;
		try {
			result = statusService.insert(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateStatusResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateStatusReqDto data) {
		UpdateStatusResDto result = null;
		try {
			result = statusService.update(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteStatusResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		DeleteStatusResDto result = null;
		try {
			result = statusService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByStatusIdResDto.class)))
	@GetMapping("/status-code")
	public ResponseEntity<?> getByStatusCode(@RequestParam String statusCode) {
		GetByStatusIdResDto result = null;
		try {
			result = statusService.getByStatusCode(statusCode);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
