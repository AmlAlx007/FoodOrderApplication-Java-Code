package com.fagito.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, String>{
	
	@Query(value="SELECT O.order_id FROM order_accept O ORDER BY O.order_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT O.order_id FROM order_accept O where O.restaurant_id=:restaurant_id",nativeQuery=true)
	List<String> getByRestaurantId(@Param("restaurant_id") String restaurant_id);
	
	@Query(value="SELECT * FROM order_accept O where O.restaurant_id=:restaurant_id",nativeQuery=true)
	List<Order> getByRId(@Param("restaurant_id") String restaurant_id);
	
	@Query(value="SELECT * FROM order_accept O where O.order_id=:order_id",nativeQuery=true)
	Order getByOrderId(@Param("order_id") String order_id);
	
	@Query(value="SELECT count(*) FROM order_accept O where O.customer_id=:customer_id",nativeQuery=true)
	int getNoOfOrders(@Param("customer_id") String customer_id);
	
}
