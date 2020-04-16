package com.fagito.controller;

import com.fagito.dto.CustomerDTO;
import com.fagito.dto.LoginDTO;
import com.fagito.dto.SignUpDTO;
import com.fagito.security.JwtUtil;
import com.fagito.service.UserServiceInterface;
import com.fagito.view.CustomerForm;
import com.fagito.view.LoginForm;
import com.fagito.view.Login_Output_to_Ui;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
public class User_Controller{
    //UserService.java
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private AuthenticationManager Authmanager;
    @Autowired
    private JwtUtil JWTutility;
	//user registration controller
    @PostMapping("/signup")
    public ResponseEntity<?> addUser(@RequestBody CustomerForm customerForm){
    	try
    	{
	    	CustomerDTO customerDTO=new CustomerDTO();
	    	SignUpDTO signupDTO=new SignUpDTO();
	    	BeanUtils.copyProperties(customerForm, customerDTO);
	    	BeanUtils.copyProperties(customerForm, signupDTO);
	    	return ResponseEntity.status(200).body(userServiceInterface.addUser(customerDTO,signupDTO));
    	}
    	catch(Exception e)
    	{
    		return ResponseEntity.status(500).body(e.getMessage());
    	}

	    
    }
    //user login controller
   @PostMapping("/login")
    public ResponseEntity<?> verifyUser(@RequestBody LoginForm loginForm)
    {
    	try
    	{
    		Login_Output_to_Ui exsist_user;
	    	LoginDTO loginDTO=new LoginDTO();
	    	BeanUtils.copyProperties(loginForm, loginDTO);
	    	exsist_user = userServiceInterface.verifyUser(loginDTO);
	    	try
	    	{
	    	Authmanager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(),loginForm.getPassword()));
	    	}
	    	catch(BadCredentialsException ex) {
	    		throw new Exception("Incorrect user name or password");
	    	}
	    	UserDetails user=userServiceInterface.getuserbyemail(loginForm.getEmail());
	     String jwttoken=JWTutility.GenerateToken(user);
	    		 exsist_user.setJwttoken(jwttoken);
	    	return ResponseEntity.status(200).body(exsist_user);
	    }
    	catch(Exception e)
    	{
    		return ResponseEntity.status(500).body(e.getMessage());
    	}
    }
}