package com.fagito.service.command;

import org.springframework.stereotype.Service;

import com.fagito.view.Add_Restaurant_UI;
@Service
public class AdminService_Switch {
	private Command_Restaurant command_Restaurant;
	public void setCommand_Restaurant(Command_Restaurant command_Restaurant)
	{
		this.command_Restaurant=command_Restaurant;
	}
	public String switchService(Add_Restaurant_UI add_restaurant_ui) throws Exception
	{
		return this.command_Restaurant.execute(add_restaurant_ui);

	}

}
