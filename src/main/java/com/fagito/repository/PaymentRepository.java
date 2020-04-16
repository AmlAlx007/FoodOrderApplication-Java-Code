package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fagito.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
	@Query(value="SELECT count(1) FROM payment p ORDER BY p.payment_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	

}
