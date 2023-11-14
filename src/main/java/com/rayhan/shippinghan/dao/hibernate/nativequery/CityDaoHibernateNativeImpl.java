package com.rayhan.shippinghan.dao.hibernate.nativequery;

import com.rayhan.shippinghan.model.City;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.rayhan.shippinghan.dao.BaseDaoImpl;
import com.rayhan.shippinghan.dao.CityDao;

@Profile("native")
@Repository(value = "cityDaoNative")
public class CityDaoHibernateNativeImpl extends BaseDaoImpl implements CityDao {

	@Override
	public List<City> getAll() throws Exception {
		String sql = "SELECT id,names,city_code,created_by,created_date,isactive,update_by,update_date,version FROM cities ";
		List<?> result = em.createNativeQuery(sql).getResultList();
		List<City> listCity = new ArrayList<>();
		result.forEach(rs -> {
			Object[] objArr = (Object[]) rs;
			City city = new City();
			city.setId(Long.valueOf(objArr[0].toString()));
			city.setNames(objArr[1].toString());
			city.setCityCode(objArr[2].toString());
			city.setCreatedBy(Long.valueOf(objArr[3].toString()));
			city.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
			listCity.add(city);

		});
		return listCity;
	}

	@Override
	public City getById(Long id) throws Exception {
		City city = null;
		try {
			String sql = "SELECT id,names,city_code,created_by,created_date,isactive,update_by,update_date,version FROM cities WHERE id = :id";
			Object result = em.createNativeQuery(sql).setParameter("id", id).getSingleResult();
			if (result != null) {
				city = new City();
				Object[] objArr = (Object[]) result;
				city.setId(Long.valueOf(objArr[0].toString()));
				city.setNames(objArr[1].toString());
				city.setCityCode(objArr[2].toString());
				city.setCreatedBy(Long.valueOf(objArr[3].toString()));
				city.setCreatedDate(((Timestamp)objArr[4]).toLocalDateTime());
				city.setIsActive((Boolean)objArr[5]);
				city.setVersion(Long.valueOf(objArr[8].toString()).intValue());
			}
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return city;
	}

	@Override
	public City insert(City data) throws Exception {
		return insertData(data);
	}

	@Override
	public City update(City data) throws Exception {
		return updateData(data);
	}

	@Override
	public Boolean deleteById(Long id) throws Exception {
		String sql = "DELETE FROM cities WHERE id = :id";
		
		int result = em.createNativeQuery(sql).setParameter("id", id).executeUpdate();
		
		return result > 0;
	}

}
