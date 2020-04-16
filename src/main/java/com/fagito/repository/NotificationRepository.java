package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fagito.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, String> {

	
	@Query(value="SELECT n.nid FROM notification n ORDER BY n.nid DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT * FROM notification n where n.oid=:orderId and n.user_id=:userId",nativeQuery=true)
	Notification findNotiByOrderId(@Param("orderId") String orderId,@Param("userId") String userId);
	
	@Query(value="SELECT * FROM notification n where n.oid=:orderId and n.user_id=:userId",nativeQuery=true)
	Notification getNotiId(@Param("orderId") String orderId,@Param("userId") String userId);
	
}
   