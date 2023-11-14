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

import com.rayhan.shippinghan.dto.roles.DeleteRolesResDto;
import com.rayhan.shippinghan.dto.roles.GetAllRolesResDto;
import com.rayhan.shippinghan.dto.roles.GetByRolesIdResDto;
import com.rayhan.shippinghan.dto.roles.InsertRolesReqDto;
import com.rayhan.shippinghan.dto.roles.InsertRolesResDto;
import com.rayhan.shippinghan.dto.roles.UpdateRolesReqDto;
import com.rayhan.shippinghan.dto.roles.UpdateRolesResDto;
import com.rayhan.shippinghan.service.RolesService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("roles")
public class RolesController extends BaseController {
	
	@Autowired
	private RolesService rolesService;
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllRolesResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		GetAllRolesResDto result = null;
		result = rolesService.getAll();
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByRolesIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id)throws Exception {
		GetByRolesIdResDto result = null;
		result = rolesService.getById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertRolesResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertRolesReqDto data)throws Exception {
		InsertRolesResDto result = rolesService.insert(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateRolesResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateRolesReqDto data) throws Exception {
		UpdateRolesResDto result = rolesService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteRolesResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws Exception {
		DeleteRolesResDto result = rolesService.deleteById(id);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
}
