package com.rayhan.shippinghan.dao.springdatajpa;

import com.rayhan.shippinghan.dao.repo.RolesRepo;
import com.rayhan.shippinghan.model.Roles;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.RolesDao;

@Profile("springjpa")
@Repository
public class RolesDaoSpringDataJpa extends BaseDaoImpl implements RolesDao{

	@Autowired
	private RolesRepo rolesRepo;
	
	@Override
	public List<Roles> getAll() throws Exception {
		return rolesRepo.findAll();
	}

	@Override
	public Roles getById(Long id) throws Exception {
		return rolesRepo.getById(id);
	}

	@Override
	public Roles insert(Roles data) throws Exception {
		return rolesRepo.saveAndFlush(data);
	}

	@Override
	public Roles update(Roles data) throws Exception {
		return rolesRepo.saveAndFlush(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		return rolesRepo.delById(id)>0;
	}

}
