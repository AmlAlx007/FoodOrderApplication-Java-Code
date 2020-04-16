package com.fagito.service;

import java.util.List;

import com.fagito.view.Food_Form;
import com.fagito.view.Food_Form_UI;

public interface FoodSearchServiceInterface {
    
	List<Food_Form> foodSearch(Food_Form_UI food_form_ui)throws Exception;
}
