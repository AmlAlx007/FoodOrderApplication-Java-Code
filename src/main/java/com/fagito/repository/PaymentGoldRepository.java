package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fagito.model.PaymentGold;

@Repository
public interface PaymentGoldRepository extends JpaRepository<PaymentGold,String> {
	
	@Query(value="SELECT p.pay_id FROM payment_gold p ORDER BY p.pay_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
}
