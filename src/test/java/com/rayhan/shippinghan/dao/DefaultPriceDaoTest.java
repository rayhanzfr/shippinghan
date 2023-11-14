package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.DefaultPriceDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.DefaultPriceDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.DefaultPrice;
import com.rayhan.shippinghan.model.ServiceType;
import java.math.BigDecimal;
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
public class DefaultPriceDaoTest {
	private static DefaultPriceDao defaultPriceDao;

	private static DefaultPriceDao defaulrPriceDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			DefaultPriceDaoHibernateHQLImpl defaultPriceDaoHQL = new DefaultPriceDaoHibernateHQLImpl();
			defaultPriceDaoHQL.setEntityManager(em);
			return defaultPriceDaoHQL;
		} else {
			DefaultPriceDaoHibernateNativeImpl defaultPriceDaoNative = new DefaultPriceDaoHibernateNativeImpl();
			defaultPriceDaoNative.setEntityManager(em);
			return defaultPriceDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {

		defaultPriceDao = defaulrPriceDaoFactory(dao, em);

	}

	@Test
	@Order(3)
	public void shoudSuccessGetAll() throws Exception {
		int results = defaultPriceDao.getAll().size();
		assertEquals(results, 6);
	}

	@Test
	@Order(4)
	public void shoudSuccessGetById() throws Exception {
		Long id = 6L;
		DefaultPrice result = defaultPriceDao.getById(id);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		DefaultPrice defaultPrice = new DefaultPrice();
		Branch branch = new Branch();
		ServiceType serviceType = new ServiceType();
		serviceType.setId(1L);
		defaultPrice.setServiceType(serviceType);
		branch.setId(1L);
		defaultPrice.setBranch(branch);
		defaultPrice.setDefaultPriceCode("9999");
		BigDecimal price = new BigDecimal(100000);
		defaultPrice.setPrice(price);
		defaultPrice.setCreatedBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		defaultPrice.setCreatedDate(localDateTime);
		defaultPrice.setIsActive(true);

		DefaultPrice result = defaultPriceDao.insert(defaultPrice);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		DefaultPrice defaultPrice = new DefaultPrice();
		Branch branch = new Branch();
		ServiceType serviceType = new ServiceType();
		defaultPrice.setId(17L);
		serviceType.setId(1L);
		defaultPrice.setServiceType(serviceType);
		branch.setId(1L);
		defaultPrice.setBranch(branch);
		defaultPrice.setDefaultPriceCode("9998");
		BigDecimal price = new BigDecimal(1000000);
		defaultPrice.setPrice(price);
		defaultPrice.setUpdateBy(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		defaultPrice.setUpdateDate(localDateTime);
		defaultPrice.setIsActive(true);

		DefaultPrice result = defaultPriceDao.update(defaultPrice);
		assertEquals(result.getBranch().getId(), 1L);
		assertEquals(result.getServiceType().getId(), 1L);
		assertEquals(result.getDefaultPriceCode(), "9998");
		assertEquals(result.getPrice(), price);
	}

	@Test
	@Transactional
	@Order(5)
	@Commit
	public void shouldSuccessDelete() throws Exception {
		long id = 6L;

		boolean result = defaultPriceDao.deleteById(id);
		assertEquals(result, true);
	}
}