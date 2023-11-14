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

import com.rayhan.shippinghan.dto.category.DeleteCategoryResDto;
import com.rayhan.shippinghan.dto.category.GetAllCategoryResDto;
import com.rayhan.shippinghan.dto.category.GetByCategoryIdResDto;
import com.rayhan.shippinghan.dto.category.InsertCategoryReqDto;
import com.rayhan.shippinghan.dto.category.InsertCategoryResDto;
import com.rayhan.shippinghan.dto.category.UpdateCategoryReqDto;
import com.rayhan.shippinghan.dto.category.UpdateCategoryResDto;
import com.rayhan.shippinghan.service.CategoryService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("categories")
public class CategoryController extends BaseController{

	@Autowired
	private CategoryService categoryService;

	@ApiResponse(responseCode = "200",description = "Success GET",content = @Content(schema = @Schema(implementation = GetAllCategoryResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() throws Exception {
		GetAllCategoryResDto result = categoryService.getAll();
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200",description = "Success GET",content = @Content(schema = @Schema(implementation = GetByCategoryIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) throws Exception {
		GetByCategoryIdResDto result = categoryService.getById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201",description = "Created",content = @Content(schema = @Schema(implementation = InsertCategoryResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertCategoryReqDto data) throws Exception {
		InsertCategoryResDto result = categoryService.insert(data);
		return new ResponseEntity<>(result,HttpStatus.CREATED);
	}
	
	@ApiResponse(responseCode = "200",description = "Updated",content = @Content(schema = @Schema(implementation = UpdateCategoryResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdateCategoryReqDto data) throws Exception {
		UpdateCategoryResDto result = categoryService.update(data);
		return new ResponseEntity<>(result,HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200",content = @Content(schema = @Schema(implementation = DeleteCategoryResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) throws Exception {
		DeleteCategoryResDto result = categoryService.deleteById(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
