package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Roles;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.RolesDao;

@Profile("hql")
@Repository
public class RolesDaoHibernateHQLImpl extends BaseDaoImpl implements RolesDao{

	@Override
	public List<Roles> getAll() throws Exception {
		List<Roles> listRoles = em.createQuery("From Roles r ",Roles.class)
				.getResultList();
		return listRoles;
	}

	@Override
	public Roles getById(Long id) throws Exception {
		Roles roles = em.find(Roles.class, id);
		em.detach(roles);
		return roles;
	}

	@Override
	public Roles insert(Roles data) throws Exception {
		return insertData(data);
	}

	@Override
	public Roles update(Roles data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM Roles WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

}
