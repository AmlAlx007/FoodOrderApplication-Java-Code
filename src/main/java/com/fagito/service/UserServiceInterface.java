package com.fagito.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.fagito.dto.CustomerDTO;
import com.fagito.dto.LoginDTO;
import com.fagito.dto.SignUpDTO;
import com.fagito.view.Login_Output_to_Ui;

public interface UserServiceInterface {
	
	public String addUser(CustomerDTO customerDTO,SignUpDTO signupDTO) throws Exception;
	public Login_Output_to_Ui verifyUser(LoginDTO loginDTO) throws Exception;
	public UserDetails getuserbyemail(String emailid);
}
