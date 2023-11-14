package com.rayhan.shippinghan.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class BaseController {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>handleValidException(MethodArgumentNotValidException e){
		Map<String,Object> listError = new HashMap<>();
		List<String> errors = new ArrayList<>();
		e.getBindingResult().getFieldErrors().forEach(error ->{
			errors.add(error.getDefaultMessage());
		});
		listError.put("message", errors);
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
		
	}
}
