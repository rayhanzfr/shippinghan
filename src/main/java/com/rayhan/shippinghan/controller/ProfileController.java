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

import com.rayhan.shippinghan.dto.profile.DeleteProfileResDto;
import com.rayhan.shippinghan.dto.profile.GetAllProfileResDto;
import com.rayhan.shippinghan.dto.profile.GetByProfileIdResDto;
import com.rayhan.shippinghan.dto.profile.GetProfileByUserIdResDto;
import com.rayhan.shippinghan.dto.profile.InsertProfileReqDto;
import com.rayhan.shippinghan.dto.profile.InsertProfileResDto;
import com.rayhan.shippinghan.dto.profile.UpdateProfileReqDto;
import com.rayhan.shippinghan.dto.profile.UpdateProfileResDto;
import com.rayhan.shippinghan.service.ProfileService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("profiles")
public class ProfileController extends BaseController{

	@Autowired
	private ProfileService profileService;
	
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllProfileResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() {
		GetAllProfileResDto result = null;
		try {
			result = profileService.getAll();
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByProfileIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		GetByProfileIdResDto result = null;
		try {
			result = profileService.getById(id);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertProfileResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertProfileReqDto data) {
		InsertProfileResDto result = null;
		try {
			result = profileService.insert(data);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage()); 
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdateProfileResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateProfileReqDto data) {
		UpdateProfileResDto result = null;
		try {
			result = profileService.update(data);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeleteProfileResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		DeleteProfileResDto result = null;
		try {
			result = profileService.deleteById(id);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetProfileByUserIdResDto.class)))
	@GetMapping("users-id/{id}")
	public ResponseEntity<?> getProfileByUsersId(@PathVariable("id") Long usersid) {
		GetProfileByUserIdResDto result = null;
		try {
			result = profileService.getProfileByUsersId(usersid);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
}
