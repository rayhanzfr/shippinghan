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

import com.rayhan.shippinghan.dto.branch.DeleteBranchResDto;
import com.rayhan.shippinghan.dto.branch.GetAllBranchResDto;
import com.rayhan.shippinghan.dto.branch.GetByBranchIdResDto;
import com.rayhan.shippinghan.dto.branch.InsertBranchReqDto;
import com.rayhan.shippinghan.dto.branch.InsertBranchResDto;
import com.rayhan.shippinghan.dto.branch.UpdateBranchReqDto;
import com.rayhan.shippinghan.dto.branch.UpdateBranchResDto;
import com.rayhan.shippinghan.service.BranchService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("branches")
public class BranchController extends BaseController{

	@Autowired
	private BranchService branchService;
	
	@ApiResponse(responseCode = "200",description = "Success GET",content = @Content(schema = @Schema(implementation = GetAllBranchResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		GetAllBranchResDto result = branchService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200",description = "Success GET",content = @Content(schema = @Schema(implementation = GetByBranchIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		GetByBranchIdResDto result = branchService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201",description = "Created ",content = @Content(schema = @Schema(implementation = InsertBranchResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertBranchReqDto data) throws Exception {
		InsertBranchResDto result = null;
		result = branchService.insert(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200",description = "Updated ",content = @Content(schema = @Schema(implementation = UpdateBranchResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateBranchReqDto data) throws Exception {
		UpdateBranchResDto result = null;
		result = branchService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200",content = @Content(schema = @Schema(implementation = DeleteBranchResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		DeleteBranchResDto result = null;
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
}
