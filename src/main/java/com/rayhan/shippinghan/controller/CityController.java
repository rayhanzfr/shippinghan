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

import com.rayhan.shippinghan.dto.city.DeleteCityResDto;
import com.rayhan.shippinghan.dto.city.GetAllCityResDto;
import com.rayhan.shippinghan.dto.city.GetByCityIdResDto;
import com.rayhan.shippinghan.dto.city.InsertCityReqDto;
import com.rayhan.shippinghan.dto.city.InsertCityResDto;
import com.rayhan.shippinghan.dto.city.UpdateCityReqDto;
import com.rayhan.shippinghan.dto.city.UpdateCityResDto;
import com.rayhan.shippinghan.service.CityService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("cities")
public class CityController extends BaseController{
	
	@Autowired
	private CityService cityService;
	
	@ApiResponse(responseCode = "200",description = "Success GET",content = @Content(schema = @Schema(implementation = GetAllCityResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() {
		GetAllCityResDto result = null;
		try {
			result = cityService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200",description = "Success GET",content = @Content(schema = @Schema(implementation = GetByCityIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		GetByCityIdResDto result = null;
		try {
			result = cityService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201",description = "Created",content = @Content(schema = @Schema(implementation = InsertCityResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertCityReqDto data) {
		InsertCityResDto result = null;
		try {
			result = cityService.insert(data);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200", description = "Updated", content = @Content(schema = @Schema(implementation = UpdateCityResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateCityReqDto data) {
		UpdateCityResDto result = null;
		try {
			result = cityService.update(data);
		}catch(Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200",content = @Content(schema = @Schema(implementation = DeleteCityResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		DeleteCityResDto result = null;
		try {
			result = cityService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
