package com.customer.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.customer.api.bean.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
