package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Profile;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.ProfileDao;

@org.springframework.context.annotation.Profile("hql")
@Repository
public class ProfileDaoHibernateHQLImpl extends BaseDaoImpl implements ProfileDao {

	@Override
	public List<Profile> getAll() throws Exception {
		List<Profile> list = em.createQuery("SELECT p From Profile as p ",Profile.class)
				.getResultList();
		return list;
	}

	@Override
	public Profile getById(Long id) throws Exception {
		Profile profile = null;
		try {			
			profile = em.createQuery("SELECT p FROM Profile as p WHERE id=:id",Profile.class)
			.setParameter("id", id)
			.getSingleResult();
		}catch(Exception e) {
			
		}
		return profile;
	}

	@Override
	public Profile insert(Profile data) throws Exception {
		return insertData(data);
	}

	@Override
	public Profile update(Profile data) throws Exception { 
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM Profile WHERE id =:id ";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

	@Override
	public Profile getByUsernameAndPass(String username, String pass) throws Exception {
		Profile profile =null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p ");
			sql.append(" FROM Profile p ");
			sql.append(" INNER JOIN FETCH p.branch b");
			sql.append(" INNER JOIN FETCH b.city ");
			sql.append(" INNER JOIN FETCH p.users u");
			sql.append(" INNER JOIN FETCH u.roles");
			sql.append(" WHERE p.users.username = :usernames AND p.users.pass= :pass ");
			profile = em.createQuery(sql.toString(),Profile.class)
				.setParameter("usernames", username)
				.setParameter("pass", pass)
				.getSingleResult();
		return profile;
		}
		catch(NoResultException e){
			e.printStackTrace();
		}
	return profile;
	}
	public Profile getByUsersId(Long usersId) throws Exception {
		Profile profile =null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" SELECT p ");
			sql.append(" FROM Profile p ");
			sql.append(" INNER JOIN FETCH p.branch b");
			sql.append(" INNER JOIN FETCH b.city ");
			sql.append(" INNER JOIN FETCH p.users u");
			sql.append(" INNER JOIN FETCH u.roles");
			sql.append(" WHERE p.users.id = :usersId");
			profile = em.createQuery(sql.toString(),Profile.class)
				.setParameter("usersId", usersId)
				.getSingleResult();
		return profile;
		}
		catch(NoResultException e){
			e.printStackTrace();
		}
	return profile;
	}
}
