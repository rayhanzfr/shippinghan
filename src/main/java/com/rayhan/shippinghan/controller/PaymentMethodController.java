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

import com.rayhan.shippinghan.dto.paymentmethod.DeletePaymentMethodResDto;
import com.rayhan.shippinghan.dto.paymentmethod.GetAllPaymentMethodResDto;
import com.rayhan.shippinghan.dto.paymentmethod.GetByPaymentMethodIdResDto;
import com.rayhan.shippinghan.dto.paymentmethod.InsertPaymentMethodReqDto;
import com.rayhan.shippinghan.dto.paymentmethod.InsertPaymentMethodResDto;
import com.rayhan.shippinghan.dto.paymentmethod.UpdatePaymentMethodReqDto;
import com.rayhan.shippinghan.dto.paymentmethod.UpdatePaymentMethodResDto;
import com.rayhan.shippinghan.service.PaymentMethodService;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("payment-methods")
public class PaymentMethodController extends BaseController {

	@Autowired
	private PaymentMethodService paymentMethodService;

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetAllPaymentMethodResDto.class)))
	@GetMapping
	public ResponseEntity<?> getAll() {
		GetAllPaymentMethodResDto result = null;
		try {
			result = paymentMethodService.getAll();
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = GetByPaymentMethodIdResDto.class)))
	@GetMapping("{id}")
	public ResponseEntity<?> getById(@PathVariable("id") Long id) {
		GetByPaymentMethodIdResDto result = null;
		try {
			result = paymentMethodService.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = InsertPaymentMethodResDto.class)))
	@PostMapping
	public ResponseEntity<?> insert(@RequestBody @Valid InsertPaymentMethodReqDto data) {
		InsertPaymentMethodResDto result = null;
		try {
			result = paymentMethodService.insert(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = UpdatePaymentMethodResDto.class)))
	@PutMapping
	public ResponseEntity<?> update(@RequestBody @Valid UpdatePaymentMethodReqDto data) {
		UpdatePaymentMethodResDto result = null;
		try {
			result = paymentMethodService.update(data);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = DeletePaymentMethodResDto.class)))
	@DeleteMapping("{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		DeletePaymentMethodResDto result = null;
		try {
			result = paymentMethodService.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage(e.getMessage());
			return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
