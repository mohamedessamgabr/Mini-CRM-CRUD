package com.essam.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.essam.springdemo.entity.Customer;
import com.essam.springdemo.service.CustomerService;
import com.essam.springdemo.util.SortUtils;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	// Inject Customer Service to this Controller
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list")
	public String listCustomers(Model model, @RequestParam(required = false) String sort) {
		
		// get customers from the service
		List<Customer> theCustomers = null;
		
		// check for sort field
		if (sort != null) {
			int sortField = Integer.parseInt(sort);
			theCustomers = customerService.getCustomers(sortField);
		} else {
			// no sort field provided ... default to sorting by last name
			theCustomers = customerService.getCustomers(SortUtils.LAST_NAME);
		}
		
		// add the customers to the model
		model.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model model) {
		
		// Create Model Attribute to bind from data
		
		Customer customer = new Customer();
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer customer) {
		customerService.saveCustomer(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("customerId") int customerId, Model model) {
		
		Customer customer = customerService.getCustomer(customerId);
		
		model.addAttribute("customer", customer);
		
		return "customer-form";
	}

	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int customerId) {
		customerService.deleteCustomer(customerId);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/search")
	public String searchCustomers(@RequestParam("searchName") String searchName, Model model) {
		
		List<Customer> customers = customerService.searchCustomers(searchName);
		model.addAttribute("customers", customers);
		return "list-customers";
	}
	
	
	
}






