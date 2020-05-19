package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sign_up")
public class SignUp
{
	@Id
	private String sign_up_id;
	private String email;
	private String password;
	private String user_id;
	public String getSign_up_id() {
		return sign_up_id;
	}
	public void setSign_up_id(String sign_up_id) {
		this.sign_up_id = sign_up_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	
}