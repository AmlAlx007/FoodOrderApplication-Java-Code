package com.fagito.dto;

import java.util.Date;



public class LoginDTO
{
	private String login_id;
	private String sign_up_id;
	private Date last_login_time;
	private String email;
	private String password;
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getSign_up_id() {
		return sign_up_id;
	}
	public void setSign_up_id(String sign_up_id) {
		this.sign_up_id = sign_up_id;
	}
	public Date getLast_login_time() {
		return last_login_time;
	}
	public void setLast_login_time(Date last_login_time) {
		this.last_login_time = last_login_time;
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
	
	
}
