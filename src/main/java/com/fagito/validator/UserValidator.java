package com.fagito.validator;

import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fagito.dto.*;
import com.fagito.exception.UserEmailValidationException;
import com.fagito.exception.UserPasswordValidationException;

@Component
public class UserValidator
{
	@Value("${spring.user.email}")
	String errorEmail;
	@Value("${spring.user.password}")
	String errorPassword;
	//validating signup details
	public boolean validate(SignUpDTO signupDTO) throws UserPasswordValidationException,UserEmailValidationException
	{
			if(checkEmail(signupDTO.getEmail()))
			{
				if(checkPassword(signupDTO.getPassword()))
				{
					return true;
				}
				else
					throw new UserPasswordValidationException("Error Password");
			}
			else
				throw new UserEmailValidationException("Error Email");
	}
	boolean checkEmail(String email)
	{
		if(Pattern.matches("[a-zA-Z0-9./]*@[a-z./]*[.][a-z]{2,3}", email))
		{
			return true;
		}
		return false;
	}
	
	boolean checkPassword(String password)
	{
		if(Pattern.matches("^.*(?=.{8,})((?=.*[!@#$%^&*()\\-_=+{};:,<.>]){1})(?=.*\\d)((?=.*[a-z]){1})((?=.*[A-Z]){1}).*$", password))
		{
			return true;
		}
		return false;
	}
	
}