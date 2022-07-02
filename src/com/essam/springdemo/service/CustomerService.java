package com.essam.springdemo.service;

import java.util.List;

import com.essam.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public void saveCustomer(Customer customer);

	public Customer getCustomer(int customerId);

	public void deleteCustomer(int customerId);

	public List<Customer> searchCustomers(String searchName);

	public List<Customer> getCustomers(int sortField);

}
