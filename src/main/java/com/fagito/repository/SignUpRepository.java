package com.fagito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fagito.model.SignUp;

@Repository
public interface SignUpRepository extends JpaRepository<SignUp,String>{
	
	@Query(value="SELECT count(1) FROM sign_up s where s.email = :email and s.password= :password",nativeQuery=true)	 
	Integer findByEmail(@Param("email") String email,@Param("password") String password);
	
	@Query(value="SELECT count(1) FROM sign_up s where s.email = :email",nativeQuery=true)	 
	Integer findByEmailbyId(@Param("email") String email);
	
	@Query(value="SELECT s.sign_up_id FROM sign_up s ORDER BY s.sign_up_id DESC LIMIT 1",nativeQuery=true)
	String findLastRecord();
	
	@Query(value="SELECT * FROM sign_up s where s.email = :email and s.password= :password",nativeQuery=true)	 
	SignUp getByEmail(@Param("email") String email,@Param("password") String password);
	
	@Query(value="SELECT s.email FROM sign_up s where s.user_id = :user_id",nativeQuery=true)	 
	String findEmailById(@Param("user_id") String user_id);
	
	@Query(value="SELECT s.sign_up_id FROM sign_up s where s.user_id= :user_id",nativeQuery=true)	 
	String getByUserId(@Param("user_id") String user_id);
	
	@Query(value="SELECT * FROM sign_up s where s.email= :email",nativeQuery=true)	 
	SignUp getUserByEmailid(@Param("email") String emailid);
}