package com.fagito.view;

import java.util.List;

import com.fagito.model.Food;

public class SendMenuDetails {

	private List<Food> vegMenu;
	private List<Food> nonVegMenu;
	public List<Food> getVegMenu() {
		return vegMenu;
	}
	public void setVegMenu(List<Food> vegMenu) {
		this.vegMenu = vegMenu;
	}
	public List<Food> getNonVegMenu() {
		return nonVegMenu;
	}
	public void setNonVegMenu(List<Food> nonVegMenu) {
		this.nonVegMenu = nonVegMenu;
	}
	
	
}
