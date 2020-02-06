package com.customer.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.api.bean.Customer;
import com.customer.api.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	CustomerRepository repository;

	public boolean addCustomer(Customer cust) {
		Customer c = repository.save(cust);
		if (c != null) {
			return true;
		}
		return false;
	}

	public Customer getCustomer(int id) {
		return repository.findOne(id);
	}

	public boolean removeCustomer(int id) {
		repository.delete(id);
		return true;
	}

	public boolean updateCustomer(Customer c, int id) {
		Customer result = repository.findOne(id);
		if (result == null) {
			return false;
		}
		c.setId(id);
		Customer updatedCust = repository.save(c);
		if (updatedCust != null) {
			return true;
		}
		return false;
	}

}
