package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.PaymentReference;

@Repository
public interface PaymentReferenceRepository extends JpaRepository<PaymentReference, String>{

	@Query(value="SELECT count(1) FROM payment_ref p where p.payment_ref=:payment_ref",nativeQuery=true)
	Integer findRecord(@Param("payment_ref") String payment_ref);
}
