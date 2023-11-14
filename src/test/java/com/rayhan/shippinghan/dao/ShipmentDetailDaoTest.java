package com.rayhan.shippinghan.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.rayhan.shippinghan.dao.hibernate.hql.ShipmentDetailDaoHibernateHQLImpl;
import com.rayhan.shippinghan.dao.hibernate.nativequery.ShipmentDetailDaoHibernateNativeImpl;
import com.rayhan.shippinghan.model.Branch;
import com.rayhan.shippinghan.model.Category;
import com.rayhan.shippinghan.model.City;
import com.rayhan.shippinghan.model.PaymentMethod;
import com.rayhan.shippinghan.model.ServiceType;
import com.rayhan.shippinghan.model.Shipment;
import com.rayhan.shippinghan.model.ShipmentDetail;
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
public class ShipmentDetailDaoTest {
	private static ShipmentDetailDao shipmentDetailDao;
	private static EntityManager emCentral;

	private static ShipmentDetailDao shipmentDetailDaoFactory(String dao, EntityManager em) {
		if (dao.equals("hql")) {
			ShipmentDetailDaoHibernateHQLImpl shipmentDetailDaoHQL = new ShipmentDetailDaoHibernateHQLImpl();
			shipmentDetailDaoHQL.setEntityManager(em);
			return shipmentDetailDaoHQL;
		} else {
			ShipmentDetailDaoHibernateNativeImpl shipmentDetailDaoNative = new ShipmentDetailDaoHibernateNativeImpl();
			shipmentDetailDaoNative.setEntityManager(em);
			return shipmentDetailDaoNative;
		}
	}

	@BeforeAll
	public static void init(@Autowired EntityManager em, @Value("${dao.test}") String dao) {
		emCentral = em;
		shipmentDetailDao = shipmentDetailDaoFactory(dao, em);

	}

	@Test
	@Order(4)
	public void shoudSuccessGetAll() throws Exception {
		int results = shipmentDetailDao.getAll().size();
		assertEquals(results, 1);
	}

	@Test
	@Transactional
	@Order(1)
	@Commit
	public void shouldSuccessInsert() throws Exception {
		Shipment shipment = new Shipment();
		ServiceType serviceType = new ServiceType();
		LocalDateTime localDateTime = LocalDateTime.now();
		serviceType.setServiceCode("9997");
		serviceType.setServiceName("Satanic");
		serviceType.setCreatedBy(1L);
		serviceType.setCreatedDate(localDateTime);
		serviceType.setIsActive(true);
		emCentral.persist(serviceType);
		City city = new City();
		city.setCityCode("9997");
		city.setNames("Satanic");
		city.setCreatedBy(1L);
		city.setCreatedDate(localDateTime);
		city.setIsActive(true);
		emCentral.persist(city);
		Branch branch = new Branch();
		branch.setCity(city);
		branch.setBranchesCode("9999");
		branch.setBranchesCode("HAHA");
		branch.setCreatedBy(1L);
		branch.setCreatedDate(localDateTime);
		branch.setIsActive(true);
		emCentral.persist(branch);
		Status status = new Status();
		status.setStatusCode("9997");
		status.setStatusName("Satanic");
		status.setCreatedBy(1L);
		status.setCreatedDate(localDateTime);
		status.setIsActive(true);
		emCentral.persist(status);
		PaymentMethod paymentMethod = new PaymentMethod();
		paymentMethod.setPaymentCode("9997");
		paymentMethod.setPaymentName("Satanic");
		paymentMethod.setCreatedBy(1L);
		paymentMethod.setCreatedDate(localDateTime);
		paymentMethod.setIsActive(true);
		emCentral.persist(paymentMethod);
		shipment.setServiceType(serviceType);
		shipment.setReceiverDestination(branch);
		shipment.setStatus(status);
		shipment.setPaymentMethod(paymentMethod);
		shipment.setShipmentNumber("9999");
		shipment.setShippingDate(localDateTime);
		shipment.setCreatedBy(1L);
		shipment.setCreatedDate(localDateTime);
		shipment.setIsActive(true);
		emCentral.persist(shipment);
		Category category = new Category();
		category.setCatCode("9997");
		category.setNames("Satanic");
		category.setCreatedBy(1L);
		category.setCreatedDate(localDateTime);
		category.setIsActive(true);
		emCentral.persist(category);
		ShipmentDetail shipmentDetail = new ShipmentDetail();
		shipmentDetail.setShipment(shipment);
		shipmentDetail.setCategory(category);
		shipmentDetail.setCreatedBy(1L);
		shipmentDetail.setCreatedBy(1L);
		shipmentDetail.setCreatedDate(localDateTime);
		shipmentDetail.setIsActive(true);

		ShipmentDetail result = shipmentDetailDao.insert(shipmentDetail);
		assertNotNull(result.getId());
	}

	@Test
	@Transactional
	@Order(2)
	@Commit
	public void shouldSuccessUpdate() throws Exception {
		ShipmentDetail shipmentDetail = shipmentDetailDao.getById(1L);
		LocalDateTime localDateTime = LocalDateTime.now();
		Shipment shipment = shipmentDetail.getShipment();
		shipment.setId(1L);
		shipmentDetail.setShipment(shipment);
		shipmentDetail.setUpdateBy(1L);
		shipmentDetail.setUpdateDate(localDateTime);
		shipmentDetail.setIsActive(true);
		shipmentDetail.setVersion(0);

		shipmentDetailDao.update(shipmentDetail);
	}

	@Test
	@Order(3)
	public void checkSuccessUpdate() throws Exception {
		ShipmentDetail result = shipmentDetailDao.getById(1L);
		assertEquals(result.getVersion(), 1);
	}

}
