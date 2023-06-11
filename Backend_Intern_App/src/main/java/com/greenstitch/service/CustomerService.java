package com.greenstitch.service;



import com.greenstitch.entity.Customer;

public interface CustomerService {

	public Customer registerCustomer(Customer customer);
	
	public Customer registerAdmin(Customer customer);
	
	public Customer updateCustomerAsAdmin( Long id);
	
	public Customer findByEmail(String email);
	
	public Customer findByPhone(String phone);
	
	public Customer findbyId(Long id);
}
