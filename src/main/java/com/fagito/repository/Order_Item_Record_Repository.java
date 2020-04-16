package com.fagito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Order_Item_Record;

@Repository
public interface Order_Item_Record_Repository extends JpaRepository<Order_Item_Record, String> {
	
	@Query(value="SELECT o.order_item_id FROM order_item_record o ORDER BY o.order_item_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT * FROM order_item_record o where o.order_id=:order_id",nativeQuery=true)
	List<Order_Item_Record> findByOrderId(@Param("order_id") String order_id);
	

}
