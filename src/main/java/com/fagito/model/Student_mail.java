package com.fagito.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="student_mail")
public class Student_mail
{
	@Id
	private int mail_id;
	private String mail_domain;
	private String university_name;
	public int getMail_id() {
		return mail_id;
	}
	public void setMail_id(int mail_id) {
		this.mail_id = mail_id;
	}
	public String getMail_domain() {
		return mail_domain;
	}
	public void setMail_domain(String mail_domain) {
		this.mail_domain = mail_domain;
	}
	public String getUniversity_name() {
		return university_name;
	}
	public void setUniversity_name(String university_name) {
		this.university_name = university_name;
	}
	
	
}