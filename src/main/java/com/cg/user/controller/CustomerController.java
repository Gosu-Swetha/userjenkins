package com.cg.user.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.cg.user.dto.CustomerDto;
import com.cg.user.dto.CustomerRequest;
import com.cg.user.dto.CustomerResponse;
import com.cg.user.entity.Customer;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.CustomerNotFoundException;
import com.cg.user.services.CustomerService;

@RestController
@RequestMapping("/user/customer")
@CrossOrigin("*")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	private static Logger logger=LogManager.getLogger();
	
	@PostMapping("/addCustomer")
	public ResponseEntity<CustomerDto> addCustomer(@Valid @RequestBody CustomerDto customer) throws CustomerAlreadyExistsException {
		logger.info("posting customet details");
		CustomerDto p1 = customerService.addCustomer(customer);
		logger.info("Customer posted");
		return new ResponseEntity<>(p1,HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCustomer/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable("id")int id , @RequestBody CustomerDto customer) throws CustomerNotFoundException{
		logger.info("posting customet details");
		CustomerDto p1 = customerService.updateCustomer(id,customer);
		logger.info("Customer posted");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCustomer/{id}")
	public ResponseEntity<CustomerDto> deleteCustomer(@PathVariable("id")int id) throws CustomerNotFoundException{
		logger.info("deleting customet details");
		CustomerDto p1 = customerService.delCustomer(id);
		logger.info("Customer deleted");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
	@GetMapping("/viewPolicies/{cid}")
	public ResponseEntity<CustomerResponse> viewCustomer(@PathVariable("cid")int id) throws CustomerNotFoundException{
		logger.info("view customet details");
		CustomerResponse p1 = customerService.viewCustomer(id);
		logger.info("Customer details recieved");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	@GetMapping("/viewAllCustomer")
	public ResponseEntity<List<CustomerRequest>> viewAllCustomer(){
		logger.info("getting all customet details");
		List<CustomerRequest> p1 = customerService.viewAllCustomer();
		logger.info("Customer details recieved");
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	@GetMapping("/getCustomerByEmail")
	public ResponseEntity<CustomerDto> getCustomerByEmail(String email)throws CustomerNotFoundException{
		CustomerDto p1 = customerService.getCustomerByEmail(email);
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	@PutMapping("/updateCustomerByEmail/{email}")
	public ResponseEntity<CustomerDto> updateCustomerByEmail(@PathVariable("email")String email,@RequestBody CustomerDto customer) throws CustomerNotFoundException{
		CustomerDto p1 = customerService.updateCustomerByEmail(email,customer);
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}
	
//	@GetMapping("/getCustomerByEmail/{email}")
//	public ResponseEntity<CustomerDto> viewCustomerByEmail(@PathVariable String email){
//		CustomerDto p1 = customerService.viewCustomerByEmail(email);
//		return new ResponseEntity<>(p1,HttpStatus.OK);
//	}
	
	@GetMapping("/pagingAndSortingCustomer/{pageNumber}/{pageSize}/{sortProperty}")
 
	@PutMapping("/updatePassword/{email}")
	public ResponseEntity<Customer> updatePassword(@PathVariable("email")String email,@RequestParam("password")String password) throws CustomerNotFoundException{
		Customer p1 = customerService.updatePasswordByEmail(email,password);
		return new ResponseEntity<>(p1,HttpStatus.OK);
	}


	
	
	
}
