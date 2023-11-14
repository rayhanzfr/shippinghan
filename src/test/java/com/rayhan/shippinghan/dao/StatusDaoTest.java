package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.StatusDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.StatusDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Status;
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
public class StatusDaoTest {
	
	private static StatusDao statusDao;
	
	private static StatusDao statusDaoFactory(String dao, EntityManager em) {
		if(dao.equals("hql")) {
			StatusDaoHibernateHQLImpl statusDaoHQL = new StatusDaoHibernateHQLImpl();
			statusDaoHQL.setEntityManager(em);
			return statusDaoHQL;
		}else {
			StatusDaoHibernateNativeImpl statusDaoNative = new StatusDaoHibernateNativeImpl();
			statusDaoNative.setEntityManager(em);
			return statusDaoNative;
		}
	}
	@BeforeAll
	public static void init(@Autowired EntityManager em,
			@Value("${dao.test}")String dao) {
		
		statusDao = statusDaoFactory(dao, em);

	}
	
	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = statusDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		Status result = statusDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Status status = new Status();
		status.setStatusCode("9997");
		status.setStatusName("Satanic");
		status.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		status.setCreatedDate(localDateTime);
		status.setIsActive(true);

		Status result = statusDao.insert(status);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Status status = new Status();
		status.setId(6L);
		status.setStatusCode("9999");
		status.setStatusName("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		status.setUpdateBy(1L);
		status.setUpdateDate(localDateTime);
		status.setIsActive(true);

		Status result = statusDao.update(status);
		assertEquals(result.getStatusCode(), "9999");
		assertEquals(result.getStatusName(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = statusDao.deleteById(id);
		assertEquals(result, true);
	}
}