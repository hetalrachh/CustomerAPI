package com.customer.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.customer.api.bean.Customer;
import com.customer.api.service.CustomerService;

@RestController
public class CustomerController {

	@Autowired
	CustomerService custService;

	// method to add a new customer
	@RequestMapping(value = "/customers/add", method = RequestMethod.POST)
	public String addCustomer(@RequestBody Customer cust) {

		boolean result = custService.addCustomer(cust);
		if (result) {
			return "New customer added";
		}
		return "failed to add new customer";

	}

	// method to get a customer based on its id
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.GET)
	public Customer getCustomer(@PathVariable("id") int id) {
		Customer c = custService.getCustomer(id);
		return c;
	}

	// method to delete customer details
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE)
	public String deleteCustomer(@PathVariable("id") int id) {
		boolean result = custService.removeCustomer(id);
		if (result) {
			return "Customer deleted";
		} else {
			return "Failed to delete customer";
		}
	}

	// update existing customer
	@RequestMapping(value = "/customers/{id}", method = RequestMethod.PUT)
	public String updateCustomer(@RequestBody Customer c, @PathVariable("id") int id) {
		boolean result = custService.updateCustomer(c, id);
		if (result) {
			return "Customer details updated";
		} else {
			return "Failed to update customer details";
		}
	}

}
