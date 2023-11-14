package com.rayhan.shippinghan.service;

public interface BaseMasterService {
	<T>T getAll() throws Exception;

	<T>T getById(Long id) throws Exception;

	<T,R>T insert(R data) throws Exception;

	<T,R>T update(R data) throws Exception;

	<T> T deleteById(Long id) throws Exception;
}
