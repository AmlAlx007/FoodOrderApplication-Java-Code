package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.RestaurantRecord;
@Repository
public interface RestaurantRecordRepository extends JpaRepository<RestaurantRecord, String>{

	@Query(value="SELECT rr.accept_gold FROM restaurant_record rr where rr.restaurant_id= :restaurant_id ",nativeQuery=true)
    boolean getIsGold(@Param("restaurant_id") String restaurant_id);
	
	@Query(value="SELECT * FROM restaurant_record rr where rr.restaurant_id=:restaurant_id",nativeQuery=true)
	RestaurantRecord findRestaurantRecord(@Param("restaurant_id")String restaurant_id);
	
	@Query(value="SELECT r.restaurant_id FROM restaurant_record r ORDER BY r.restaurant_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
}
