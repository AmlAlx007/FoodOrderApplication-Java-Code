package com.fagito.controller;

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

import com.fagito.dto.CustomerDTO;
import com.fagito.dto.LoginDTO;
import com.fagito.dto.SignUpDTO;
import com.fagito.exception.UserWrongCredentialsLogIn;
import com.fagito.security.JwtUtil;
import com.fagito.service.UserServiceInterface;
import com.fagito.view.CustomerForm;
import com.fagito.view.LoginForm;
import com.fagito.view.Login_Output_to_Ui;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/User")
public class UserController{
	@Autowired
	private UserServiceInterface userServiceInterface;
	@Autowired
	private AuthenticationManager authManager;
    @Autowired
    private JwtUtil jwtUtility;
	/*
	 * SignUp Service
	 * --------------
	 * Controller for the User Sign Up Module. This let's the user to register with the application.
	 * 
	 * Return Type (String) - A success message
	 */
    @PostMapping("/signup")
    public ResponseEntity<String> signupUser(@RequestBody CustomerForm customerForm){
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
    /*
     * LogIn Service
     * -------------
     * The controller for the user log in functionality. User to login with the credentials.Validation of the credentials happens here.
     * JWT authentication mechanism is implemented here.
     * 
     * Return Type (Login_Output_to_Ui) - includes customer name, customer id and JWT token. 
     */
   @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginForm loginForm)
    {
    	try
    	{
    		Login_Output_to_Ui exsistUser;
	    	LoginDTO loginDTO=new LoginDTO();
	    	BeanUtils.copyProperties(loginForm, loginDTO);
	    	exsistUser = userServiceInterface.verifyUser(loginDTO);
	    	authenticate(loginForm);
	    	UserDetails user=userServiceInterface.getuserbyemail(loginForm.getEmail());
	     String jwttoken=jwtUtility.GenerateToken(user);
	    		 exsistUser.setJwttoken(jwttoken);
	    	return ResponseEntity.status(200).body(exsistUser);
	    }
    	catch(Exception e)
    	{
    		return ResponseEntity.status(500).body(e.getMessage());
    	}
    }
    public void authenticate(LoginForm loginForm) throws UserWrongCredentialsLogIn
    {
    	try
    	{
    		authManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getEmail(),loginForm.getPassword()));
    	}
    	catch(BadCredentialsException ex) {
    		throw new UserWrongCredentialsLogIn("Incorrect user name or password");
    	}
    }
}