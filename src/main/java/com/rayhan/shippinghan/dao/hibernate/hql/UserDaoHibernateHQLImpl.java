package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Users;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.UserDao;

@Profile("hql")
@Repository
public class UserDaoHibernateHQLImpl extends BaseDaoImpl implements UserDao{

	@Override
	public List<Users> getAll() throws Exception {
		String sql = "SELECT u FROM Users as u INNER JOIN FETCH u.roles";
		List<Users> result = em.createQuery(sql,Users.class).getResultList();
		return result;
	}

	@Override
	public Users getById(Long id) throws Exception {
		Users users = null;
		try {
			users = em.find(Users.class, id);
			em.detach(users);
			return users;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public Users insert(Users data) throws Exception {
		return insertData(data);
	}

	@Override
	public Users update(Users data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM Users WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

	@Override
	public Users getByUsername(String usernames) throws Exception {
		String sql = "SELECT u FROM Users u INNER JOIN FETCH u.roles WHERE u.username = :username";
		Users users = em.createQuery(sql,Users.class)
				.setParameter("username", usernames)
				.getSingleResult();
		em.detach(users);
		return users;
	}

}
