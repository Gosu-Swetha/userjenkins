package com.cg.user.services;

import java.util.List;

import org.springframework.data.domain.Page;


import com.cg.user.dto.CustomerDto;
import com.cg.user.dto.CustomerRequest;
import com.cg.user.dto.CustomerResponse;
import com.cg.user.entity.Customer;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.CustomerNotFoundException;

public interface CustomerService {
	CustomerDto addCustomer(CustomerDto parent) throws CustomerAlreadyExistsException;

	CustomerDto updateCustomer(int id , CustomerDto parent) throws CustomerNotFoundException;

	CustomerDto delCustomer(int id) throws CustomerNotFoundException;

	CustomerResponse viewCustomer(int id) throws CustomerNotFoundException;

	List<CustomerRequest> viewAllCustomer();


	CustomerDto getCustomerByEmail(String email);

	CustomerDto updateCustomerByEmail(String email, CustomerDto parent) throws CustomerNotFoundException;

	CustomerDto viewCustomerByEmail(String email);

	
	Customer updatePasswordByEmail(String email, String password) throws CustomerNotFoundException;
	
	

}
