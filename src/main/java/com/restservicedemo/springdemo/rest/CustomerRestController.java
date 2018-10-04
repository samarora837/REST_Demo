package com.restservicedemo.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restservicedemo.springdemo.entity.Customer;
import com.restservicedemo.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;
	private Customer customer;

	// add mapping for GET /customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();

	}

	// add mapping for GET /customer by ID
	@GetMapping("/customers/{customerId}")
	public Customer getCustomer(@PathVariable int customerId) {

		customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer Id not found: " + customerId);
		}
		return customer;

	}

	// add mapping for POST - add customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {

		// this will force to insert a new record instead of update
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;

	}

	// add mapping for POST - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {

		// this will force to insert a new record instead of update

		customerService.saveCustomer(theCustomer);
		return theCustomer;

	}

	// add mapping for GET /customer -delete by ID
	@DeleteMapping("/customers/{customerId}")
	public String deleteCustomer(@PathVariable int customerId) {

		customer = customerService.getCustomer(customerId);
		if (customer == null) {
			throw new CustomerNotFoundException("Customer Id not found: " + customerId);
		}
		customerService.deleteCustomer(customerId);
		return "Deleted the record for Customer ID: ; +customerId";

	}

}
