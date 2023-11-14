package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.CategoryDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.CategoryDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Category;
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
public class CategoryDaoTest {
	private static CategoryDao categoryDao;

	private static CategoryDao categoryDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			CategoryDaoHibernateHQLImpl categoryDaoHQL = new CategoryDaoHibernateHQLImpl();
			categoryDaoHQL.setEntityManager(em);
			return categoryDaoHQL;
		} else {
			CategoryDaoHibernateNativeImpl categoryDaoNative = new CategoryDaoHibernateNativeImpl();
			categoryDaoNative.setEntityManager(em);
			return categoryDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		categoryDao = categoryDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = categoryDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		Category result = categoryDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Category category = new Category();
		category.setCatCode("9997");
		category.setNames("Satanic");
		category.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		category.setCreatedDate(localDateTime);
		category.setIsActive(true);

		Category result = categoryDao.insert(category);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		Category category = new Category();
		category.setId(6L);
		category.setCatCode("9999");
		category.setNames("HEHE");
		LocalDateTime localDateTime = LocalDateTime.now();
		category.setUpdateBy(1L);
		category.setUpdateDate(localDateTime);
		category.setIsActive(true);

		Category result = categoryDao.update(category);
		assertEquals(result.getCatCode(), "9999");
		assertEquals(result.getNames(), "HEHE");
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = categoryDao.deleteById(id);
		assertEquals(result, true);
	}
}