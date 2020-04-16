package com.fagito.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="login")
public class Login
{
	@Id
	private String login_id;
	private String sign_up_id;
	private Date last_login_time;
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
	
	
}