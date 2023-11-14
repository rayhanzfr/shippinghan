package com.rayhan.shippinghan.service;

import com.rayhan.shippinghan.constant.Message;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rayhan.shippinghan.dao.CityDao;
import com.rayhan.shippinghan.dto.InsertResDataDto;
import com.rayhan.shippinghan.dto.UpdateResDataDto;
import com.rayhan.shippinghan.dto.city.DeleteCityResDto;
import com.rayhan.shippinghan.dto.city.GetAllCityResDto;
import com.rayhan.shippinghan.dto.city.GetByCityIdResDto;
import com.rayhan.shippinghan.dto.city.GetCityDataDto;
import com.rayhan.shippinghan.dto.city.InsertCityReqDto;
import com.rayhan.shippinghan.dto.city.InsertCityResDto;
import com.rayhan.shippinghan.dto.city.UpdateCityReqDto;
import com.rayhan.shippinghan.dto.city.UpdateCityResDto;
import com.rayhan.shippinghan.model.City;

@Service
public class CityServiceImpl implements CityService {
	private CityDao cityDao;
	
	@Autowired
	public void setCityDao(CityDao cityDao) {
		this.cityDao = cityDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getAll() throws Exception {
		GetAllCityResDto getAllCityResDto = new GetAllCityResDto();
		List<GetCityDataDto> listGetCityDataDto = new ArrayList<>();
		List<City> listCity = cityDao.getAll();
		listCity.forEach(r ->{
			GetCityDataDto getCityDataDto = new GetCityDataDto();
			getCityDataDto.setId(r.getId());
			getCityDataDto.setCode(r.getCityCode());
			getCityDataDto.setNames(r.getNames());
			getCityDataDto.setCreatedBy(r.getId());
			getCityDataDto.setCreatedDate(r.getCreatedDate());
			getCityDataDto.setUpdateBy(r.getUpdateBy());
			getCityDataDto.setUpdateDate(r.getUpdateDate());
			getCityDataDto.setIsActive(r.getIsActive());
			getCityDataDto.setVersion(r.getVersion());
			listGetCityDataDto.add(getCityDataDto);
		});
		getAllCityResDto.setData(listGetCityDataDto);
		return (T) getAllCityResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getById(Long id) throws Exception {
		GetByCityIdResDto getByCityIdResDto = new GetByCityIdResDto();
		GetCityDataDto getCityDataDto = new GetCityDataDto();
		City city = cityDao.getById(id);
		getCityDataDto.setCode(city.getCityCode());
		getCityDataDto.setNames(city.getNames());
		getCityDataDto.setCreatedBy(city.getId());
		getCityDataDto.setCreatedDate(city.getCreatedDate());
		getCityDataDto.setUpdateBy(city.getUpdateBy());
		getCityDataDto.setUpdateDate(city.getUpdateDate());
		getCityDataDto.setIsActive(city.getIsActive());
		getCityDataDto.setVersion(city.getVersion());
		getByCityIdResDto.setData(getCityDataDto);
		return (T) getByCityIdResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T,R> T insert(R data) throws Exception {
		InsertCityResDto insertCityResDto = new InsertCityResDto();;
		InsertResDataDto insertResDataDto = new InsertResDataDto();
		InsertCityReqDto insertCityReqDto = (InsertCityReqDto) data;
		City city = new City();
		city.setCityCode(insertCityReqDto.getCode());
		city.setNames(insertCityReqDto.getNames());
		city.setCreatedBy(insertCityReqDto.getCreatedBy());
		city.setIsActive(insertCityReqDto.getIsActive());
		insertResDataDto.setId(cityDao.insert(city).getId());
		insertCityResDto.setData(insertResDataDto);
		insertCityResDto.setMessage(Message.SUCCESS.getNames());
		return (T) insertCityResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T,R> T update(R data) throws Exception {
		UpdateCityResDto updateCityResDto = new UpdateCityResDto();
		UpdateResDataDto updateResDataDto = new UpdateResDataDto();
		UpdateCityReqDto updateCityReqDto = (UpdateCityReqDto) data;
		City city = cityDao.getById(updateCityReqDto.getId());
		city.setNames(updateCityReqDto.getNames());
		city.setUpdateBy(updateCityReqDto.getUpdateBy());
		city.setIsActive(updateCityReqDto.getIsActive());
		city.setVersion(updateCityReqDto.getVersion());
		updateResDataDto.setVersion(cityDao.update(city).getVersion());
		updateCityResDto.setData(updateResDataDto);
		updateCityResDto.setMessage(Message.SUCCESS.getNames());
		return (T) updateCityResDto;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(rollbackOn = Exception.class)
	public <T> T deleteById(Long id) throws Exception {
		DeleteCityResDto deleteCityResDto = new DeleteCityResDto();
		cityDao.deleteById(id);
		deleteCityResDto.setMessage(Message.SUCCESS.getNames());
		return (T) deleteCityResDto;
		
	}

}
