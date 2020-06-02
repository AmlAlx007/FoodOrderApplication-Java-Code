package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.RestaurantRating;

@Repository
public interface RestaurantRatingRepository extends JpaRepository<RestaurantRating,String>{

	@Query(value="SELECT rr.rating FROM restaurant_rating rr where rr.restaurant_id= :restaurant_id ",nativeQuery=true)
	int findByIdRestaurantId(@Param("restaurant_id") String restaurantId);
}
