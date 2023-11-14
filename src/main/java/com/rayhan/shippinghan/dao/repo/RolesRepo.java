package com.rayhan.shippinghan.dao.repo;

import com.rayhan.shippinghan.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepo extends JpaRepository<Roles, Long> {
	@Query(value="FROM Roles WHERE roleCode = :roleCode ")
	Roles findByRoleCode(@Param("roleCode") String roleCode)throws Exception;
	
	@Modifying
	@Query(value="DELETE FROM Roles Where id=:id ")
	Integer delById(Long id);
//	Roles findByRoleCode(String roleCode)throws Exception;
	
}
