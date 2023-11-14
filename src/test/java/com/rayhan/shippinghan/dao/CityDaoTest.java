package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.CityDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.CityDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.City;
import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;

@DataJpaTest
@TestMethodOrder(OrderAnnotation.class)
public class CityDaoTest {
	private static CityDao cityDao;

	private static CityDao cityDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			CityDaoHibernateHQLImpl cityDaoHQL = new CityDaoHibernateHQLImpl();
			cityDaoHQL.setEntityManager(em);
			return cityDaoHQL;
		} else {
			CityDaoHibernateNativeImpl cityDaoNative = new CityDaoHibernateNativeImpl();
			cityDaoNative.setEntityManager(em);
			return cityDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		cityDao = cityDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = cityDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		City result = cityDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		City city = new City();
		city.setCityCode("9997");
		city.setNames("Satanic");
		city.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		city.setCreatedDate(localDateTime);
		city.setIsActive(true);

		City result = cityDao.insert(city);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		City city = new City();
		city.setId(6L);
		city.setCityCode("9999");
		city.setNames("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		city.setUpdateBy(1L);
		city.setUpdateDate(localDateTime);
		city.setIsActive(true);

		City result = cityDao.update(city);
		assertEquals(result.getCityCode(), "9999");
		assertEquals(result.getNames(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = cityDao.deleteById(id);
		assertEquals(result, true);
	}
}