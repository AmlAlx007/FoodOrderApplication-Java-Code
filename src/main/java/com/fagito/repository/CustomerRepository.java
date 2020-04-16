package com.fagito.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fagito.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String>{
	
	@Query(value="SELECT c.customer_id FROM customer c WHERE c.usertype=:usertype ORDER BY c.customer_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord(@Param("usertype")String usertype);
	
	@Query(value="SELECT * FROM customer c where c.customer_id = :customer_id",nativeQuery=true)
	Customer findByCustomerId(@Param("customer_id") String customer_id);
	
	@Query(value="SELECT * FROM customer c where c.is_gold=1 ", nativeQuery=true)
	List<Customer> findAllCustomers();
	
	@Query(value="SELECT c.customer_name FROM customer c where c.customer_id = :customer_id",nativeQuery=true)
	String findNameByCustomerId(@Param("customer_id") String customer_id);
	
	@Query(value="SELECT c.membership FROM customer c where c.customer_id = :customer_id",nativeQuery=true)
	String findGoldByCustomerId(@Param("customer_id") String customer_id);
}
