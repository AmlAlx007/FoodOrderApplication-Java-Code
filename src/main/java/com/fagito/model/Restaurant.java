package com.fagito.model;

import lombok.*;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="restaurant")
public class Restaurant {
	
	@Id
	private String restaurant_id;
	private String restaurant_name;
	private Timestamp opening_time;
	private Timestamp closing_time;
	private int student_discount;
	private int general_discount;
	private String cusine_type;
	private String days;
	
}
