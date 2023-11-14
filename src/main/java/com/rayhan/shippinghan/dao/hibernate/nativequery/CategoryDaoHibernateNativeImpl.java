package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.Category;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.CategoryDao;

@Profile("native")
@Repository(value = "categoryDaoNative")
public class CategoryDaoHibernateNativeImpl extends BaseDaoImpl implements CategoryDao{
	
	@Override
	public List<Category> getAll() throws Exception {
		String sql = "SELECT id,names,cat_code FROM categories ORDER BY id";
		List<?> result = em.createNativeQuery(sql.toString()).getResultList();
		List<Category> listCategory = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			Category category = new Category();
			category.setId(Long.valueOf(objArr[0].toString()));
			category.setNames(objArr[1].toString());
			category.setCatCode(objArr[2].toString());
			listCategory.add(category);
			
		});
		return listCategory;
	}

	@Override
	public Category getById(Long id) throws Exception {
		Category category = null;
		try {
			String sql = "SELECT id,names,cat_code,created_by,created_date,isactive,update_by,update_date,version FROM categories WHERE id = :id";
			Object result =  em.createNativeQuery(sql)
					.setParameter("id", id)
					.getSingleResult();
			if(result!=null) {
				category = new Category();
				Object[] objArr = (Object[]) result;
				category.setId(Long.valueOf(objArr[0].toString()));
				category.setNames(objArr[1].toString());
				category.setCatCode(objArr[2].toString());
				category.setCreatedBy(Long.valueOf(objArr[3].toString()));
				category.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
				if(objArr[8]!=null) {					
					category.setVersion(Long.valueOf(objArr[8].toString()).intValue());
				}
			}
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
		String sql="DELETE FROM categories WHERE id = :id";
		
		int result = em.createNativeQuery(sql)
			.setParameter("id", id)
			.executeUpdate();
		
		return result>0;
	}

}
