package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant,String>{
	
	@Query(value="SELECT * FROM restaurant r where r.restaurant_id= :restaurant_id",nativeQuery=true)
	Restaurant findByRestaurantId(@Param("restaurant_id") String restaurant_id);
	
	@Query(value="SELECT r.restaurant_id FROM restaurant r ORDER BY r.restaurant_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT r.restaurant_id FROM restaurant r where r.restaurant_name= :restaurant_name",nativeQuery=true)
	String findByRestaurantName(@Param("restaurant_name") String restaurant_name);
}
