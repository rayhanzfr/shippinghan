package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.CategoryDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.category.DeleteCategoryResDto;
import com.rayhan.shippinghan.dto.category.GetAllCategoryResDto;
import com.rayhan.shippinghan.dto.category.GetByCategoryIdResDto;
import com.rayhan.shippinghan.dto.category.GetCategoryDataDto;
import com.rayhan.shippinghan.dto.category.InsertCategoryReqDto;
import com.rayhan.shippinghan.dto.category.InsertCategoryResDto;
import com.rayhan.shippinghan.dto.category.UpdateCategoryReqDto;
import com.rayhan.shippinghan.dto.category.UpdateCategoryResDto;
import com.rayhan.shippinghan.model.Category;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao categoryDao;
	
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllCategoryResDto getAllCategoryResDto = new GetAllCategoryResDto();
		List<GetCategoryDataDto> listGetCategoryDataDto = new ArrayList<>();
		List<Category> listCategory = categoryDao.getAll();
		listCategory.forEach(r ->{
			GetCategoryDataDto getCategoryDataDto = new GetCategoryDataDto();
			getCategoryDataDto.setId(r.getId());
			getCategoryDataDto.setCode(r.getCatCode());
			getCategoryDataDto.setNames(r.getNames());
			getCategoryDataDto.setCreatedBy(r.getId());
			getCategoryDataDto.setCreatedDate(r.getCreatedDate());
			getCategoryDataDto.setUpdateBy(r.getUpdateBy());
			getCategoryDataDto.setUpdateDate(r.getUpdateDate());
			getCategoryDataDto.setIsActive(r.getIsActive());
			getCategoryDataDto.setVersion(r.getVersion());
			listGetCategoryDataDto.add(getCategoryDataDto);
		});
		getAllCategoryResDto.setData(listGetCategoryDataDto);
		return (T) getAllCategoryResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByCategoryIdResDto getByCategoryIdResDto = new GetByCategoryIdResDto();
		GetCategoryDataDto getCategoryDataDto = new GetCategoryDataDto();
		Category category = new Category();
		category = categoryDao.getById(id);
		getCategoryDataDto.setId(category.getId());
		getCategoryDataDto.setCode(category.getCatCode());
		getCategoryDataDto.setNames(category.getNames());
		getCategoryDataDto.setCreatedBy(category.getId());
		getCategoryDataDto.setCreatedDate(category.getCreatedDate());
		getCategoryDataDto.setUpdateBy(category.getUpdateBy());
		getCategoryDataDto.setUpdateDate(category.getUpdateDate());
		getCategoryDataDto.setIsActive(category.getIsActive());
		getCategoryDataDto.setVersion(category.getVersion());
		getByCategoryIdResDto.setData(getCategoryDataDto);
		return (T) getByCategoryIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T,R> T insert(R data) throws Exception {
		InsertCategoryResDto insertCategoryResDto = new InsertCategoryResDto();;
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertCategoryReqDto insertCategoryReqDto = (InsertCategoryReqDto) data;
		Category category = new Category();
		category.setCatCode(insertCategoryReqDto.getCode());
		category.setNames(insertCategoryReqDto.getNames());
		category.setCreatedBy(insertCategoryReqDto.getCreatedBy());
		category.setIsActive(insertCategoryReqDto.getIsActive());
		insertResDataDto.setId(categoryDao.insert(category).getId());
		insertCategoryResDto.setData(insertResDataDto);
		insertCategoryResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertCategoryResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T,R> T update(R data) throws Exception {
		UpdateCategoryResDto updateCategoryResDto = new UpdateCategoryResDto();
		UpdateCategoryReqDto updateCategoryReqDto = (UpdateCategoryReqDto) data;
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		Category category = categoryDao.getById(updateCategoryReqDto.getId());
		category.setNames(updateCategoryReqDto.getNames());
		category.setUpdateBy(updateCategoryReqDto.getUpdateBy());
		category.setIsActive(updateCategoryReqDto.getIsActive());
		category.setVersion(updateCategoryReqDto.getVersion());
		updateResDataDto.setVersion(categoryDao.update(category).getVersion());
		updateCategoryResDto.setData(updateResDataDto);
		updateCategoryResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateCategoryResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteCategoryResDto deleteCategoryResDto = new DeleteCategoryResDto();
		categoryDao.deleteById(id);
		deleteCategoryResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteCategoryResDto;
		
	}

}
