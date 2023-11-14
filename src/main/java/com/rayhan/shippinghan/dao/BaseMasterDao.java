package com.rayhan.shippinghan.dao;

import java.util.List;

public interface BaseMasterDao<T> {
	List<T> getAll() throws Exception;

	T getById(Long id) throws Exception;

	T insert(T data) throws Exception;

	T update(T data) throws Exception;

	Boolean deleteById(Long id) throws Exception;
}
