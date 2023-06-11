package com.greenstitch.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.greenstitch.entity.Customer;
import com.greenstitch.entity.Role;
import com.greenstitch.exception.CustomerException;
import com.greenstitch.exception.CustomerNotFoundException;
import com.greenstitch.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Customer registerCustomer(Customer customer)  {
		// TODO Auto-generated method stub
		Optional<Customer> cust = customerRepository.findByEmail(customer.getEmail());
		if(cust.isEmpty()) {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			customer.setRole(Role.ROLE_CUSTOMER);
			return customerRepository.save(customer);
		}else {
			throw new CustomerException("Customer is already register with this email : "+customer.getEmail());
		}
	}

	@Override
	public Customer registerAdmin(Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> cust = customerRepository.findByEmail(customer.getEmail());
		if(cust.isEmpty()) {
			customer.setPassword(passwordEncoder.encode(customer.getPassword()));
			customer.setRole(Role.ROLE_ADMIN);
			return customerRepository.save(customer);
		}else {
			throw new CustomerException("User is already register with this email : "+customer.getEmail());
		}
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return   customerRepository.findByEmail(email).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found with this email "+ email));
	     
	}

	@Override
	public Customer findByPhone(String phone) {
		
		return   customerRepository.findByPhone(phone).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found with this phone "+ phone));
	}

	@Override
	public Customer findbyId(Long id) {
		// TODO Auto-generated method stub
		return   customerRepository.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer Not Found with this id "+ id));
	}

	@Override
	public Customer updateCustomerAsAdmin( Long id) {
		// TODO Auto-generated method stub
		Customer cust = findbyId(id);
		if(cust != null) {
			cust.setRole(Role.ROLE_ADMIN);
			return customerRepository.save(cust);
		}else {
			throw new CustomerNotFoundException("Customer is not register with this id : "+id);
		}
	}

}
