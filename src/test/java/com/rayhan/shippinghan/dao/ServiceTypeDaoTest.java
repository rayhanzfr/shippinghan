package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.ServiceTypeDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.ServiceTypeDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.ServiceType;
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
public class ServiceTypeDaoTest {
	private static ServiceTypeDao serviceTypeDao;

	private static ServiceTypeDao serviceTypeDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			ServiceTypeDaoHibernateHQLImpl serviceTypeDaoHQL = new ServiceTypeDaoHibernateHQLImpl();
			serviceTypeDaoHQL.setEntityManager(em);
			return serviceTypeDaoHQL;
		} else {
			ServiceTypeDaoHibernateNativeImpl serviceTypeDaoNative = new ServiceTypeDaoHibernateNativeImpl();
			serviceTypeDaoNative.setEntityManager(em);
			return serviceTypeDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		serviceTypeDao = serviceTypeDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = serviceTypeDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		ServiceType result = serviceTypeDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		ServiceType serviceType = new ServiceType();
		serviceType.setServiceCode("9997");
		serviceType.setServiceName("Satanic");
		serviceType.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		serviceType.setCreatedDate(localDateTime);
		serviceType.setIsActive(true);

		ServiceType result = serviceTypeDao.insert(serviceType);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		ServiceType serviceType = new ServiceType();
		serviceType.setId(6L);
		serviceType.setServiceCode("9999");
		serviceType.setServiceName("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		serviceType.setUpdateBy(1L);
		serviceType.setUpdateDate(localDateTime);
		serviceType.setIsActive(true);

		ServiceType result = serviceTypeDao.update(serviceType);
		assertEquals(result.getServiceCode(), "9999");
		assertEquals(result.getServiceName(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = serviceTypeDao.deleteById(id);
		assertEquals(result, true);
	}
}