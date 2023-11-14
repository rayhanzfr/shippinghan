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

import com.rayhan.shippinghan.dto.users.DeleteUsersResDto;
import com.rayhan.shippinghan.dto.users.GetAllUsersResDto;
import com.rayhan.shippinghan.dto.users.GetByUsersIdResDto;
import com.rayhan.shippinghan.dto.users.InsertUsersReqDto;
import com.rayhan.shippinghan.dto.users.InsertUsersResDto;
import com.rayhan.shippinghan.dto.users.UpdateUsersReqDto;
import com.rayhan.shippinghan.dto.users.UpdateUsersResDto;
import com.rayhan.shippinghan.service.UserService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("users")
public class UsersController {

	@Autowired
	private UserService userService;

	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllUsersResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() {
		GetAllUsersResDto result = null;
		try {
			result = userService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByUsersIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		GetByUsersIdResDto result = null;
		try {
			result = userService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertUsersResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertUsersReqDto data) {
		InsertUsersResDto result = null;
		try {
			result = userService.insert(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateUsersResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateUsersReqDto data) {
		UpdateUsersResDto result = null;
		try {
			result = userService.update(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteUsersResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		DeleteUsersResDto result = null;
		try {
			result = userService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
