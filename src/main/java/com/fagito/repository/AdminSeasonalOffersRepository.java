package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.AdminSeasonalOffers;

@Repository
public interface AdminSeasonalOffersRepository extends JpaRepository<AdminSeasonalOffers, String>{
	
	@Query(value="SELECT a.offer_id FROM admin_seasonal_offers a ORDER BY a.offer_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT * FROM admin_seasonal_offers a ORDER BY a.offer_id DESC LIMIT 1",nativeQuery=true)
	AdminSeasonalOffers findLatest();
	
	@Query(value="select * from admin_seasonal_offers a where a.user_type=:userType and curdate()< a.is_offer_expired order by a.is_offer_expired desc LIMIT 1",nativeQuery=true)
	AdminSeasonalOffers findLatestByDate(@Param("userType")String userType);

}
