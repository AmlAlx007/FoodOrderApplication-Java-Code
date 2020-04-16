package com.fagito.service.abstractfactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fagito.model.Customer;
import com.fagito.repository.CustomerRepository;
import com.fagito.repository.OrderRepository;

@Component
public class StudentCalculateReward extends CalculateRewardAbstract{

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;	

	public float calculateRewardPoints(float actual_amount,String customerId) {
		
		int numberOfOrders=orderRepository.getNoOfOrders(customerId);
		Customer customer=customerRepository.findByCustomerId(customerId);
		customer.setRewardpoints(customer.getRewardpoints()+(numberOfOrders/2));
		customerRepository.save(customer);
		return customer.getRewardpoints();
		
		
	}
}
