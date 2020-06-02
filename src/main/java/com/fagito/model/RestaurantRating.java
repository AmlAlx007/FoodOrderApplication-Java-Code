package com.fagito.model;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Getter
@Setter
public class RestaurantRating {

	@Id
	private String rating_id;
	private String restaurant_id;
	private int rating;
}
