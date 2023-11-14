package com.rayhan.shippinghan.dao.hibernate.hql;

import com.rayhan.shippinghan.model.Category;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.CategoryDao;

@Profile("hql")
@Repository
public class CategoryDaoHibernateHQLImpl extends BaseDaoImpl implements CategoryDao{

	@Override
	public List<Category> getAll() throws Exception {
		String sql = "SELECT c FROM Category as c";
		List<Category> result = em.createQuery(sql,Category.class).getResultList();
		return result;
	}

	@Override
	public Category getById(Long id) throws Exception {
		Category category = null;
		try {
			category =  em.find(Category.class, id);
			em.detach(category);
			return category;
		}catch(NoResultException e){
			e.printStackTrace();
		}
		return category;
	}

	@Override
	public Category insert(Category data) throws Exception {
		return insertData(data);
	}

	@Override
	public Category update(Category data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql="DELETE FROM Category WHERE id = :id";
		
		int result = em.createQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}
}
