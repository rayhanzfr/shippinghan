package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.BranchDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.BranchDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Branch;
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
public class BranchDaoTest {
	private static BranchDao branchDao;
	private static CityDao cityDao;

	private static BranchDao branchDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			BranchDaoHibernateHQLImpl branchDaoHQL = new BranchDaoHibernateHQLImpl();
			branchDaoHQL.setEntityManager(em);
			return branchDaoHQL;
		} else {
			BranchDaoHibernateNativeImpl branchDaoNative = new BranchDaoHibernateNativeImpl();
			branchDaoNative.setEntityManager(em);
			return branchDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		branchDao = branchDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = branchDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		Branch result = branchDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Branch branch = new Branch();
		City city = new City();
		city.setId(5L);
		branch.setCity(city);
		branch.setBranchesCode("9999");
		branch.setBranchesCode("HAHA");
		branch.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		branch.setCreatedDate(localDateTime);
		branch.setIsActive(true);

		Branch result = branchDao.insert(branch);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Branch branch = new Branch();
		City city = cityDao.getById(3L);
		branch.setCity(city);
		branch.setId(6L);
		branch.setBranchesCode("9999");
		branch.setBranchesName("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		branch.setUpdateBy(1L);
		branch.setUpdateDate(localDateTime);
		branch.setIsActive(true);
		branch.setVersion(0);

		Branch result = branchDao.update(branch);
		assertEquals(result.getCity().getId(), 5L);
		assertEquals(result.getBranchesCode(), "9999");
		assertEquals(result.getBranchesName(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = branchDao.deleteById(id);
		assertEquals(result, true);
	}
}
