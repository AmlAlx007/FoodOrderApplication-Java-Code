package com.fagito.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,String>{
	
	@Query(value="SELECT * FROM food f where lower(f.food_name) like %:food_name% ",nativeQuery=true)
	List<Food> findByName(@Param("food_name") String food_name);
	
	@Query(value="SELECT * FROM food f where f.menu_id=:menu_id",nativeQuery=true)
	List<Food> findByMenuId(@Param("menu_id") String menu_id);
	
	@Query(value="SELECT f.food_name FROM food f where f.food_id=:food_id",nativeQuery=true)
	String getById(@Param("food_id") String food_id);
	
}
