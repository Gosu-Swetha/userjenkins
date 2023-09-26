package com.cg.user.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.cg.user.dto.CustomerDto;
import com.cg.user.dto.CustomerRequest;
import com.cg.user.dto.CustomerResponse;
import com.cg.user.entity.Customer;
import com.cg.user.entity.Payment;
import com.cg.user.entity.Policy;
import com.cg.user.entity.Role;
import com.cg.user.entity.User;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.CustomerNotFoundException;
import com.cg.user.repository.CustomerRepository;
import com.cg.user.repository.UserRepository;
import com.cg.user.services.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private Logger logger=LoggerFactory.getLogger(CustomerServiceImpl.class);


	@Override
	public CustomerDto addCustomer(CustomerDto customer) throws CustomerAlreadyExistsException {
		Customer p1 = new Customer();
		BeanUtils.copyProperties(customer, p1);
		User u1 = new User();
		u1.setUserName(customer.getUserName());
		u1.setPassword(customer.getPassword());
		u1.setRole(Role.CUSTOMER);
		userService.userSignUp(u1);
		customerRepo.save(p1);
		
		return customer;
	}

	@Override
	public CustomerDto updateCustomer(int id, CustomerDto customer) throws CustomerNotFoundException {
		Customer p1 = customerRepo.findById(id).orElse(null);
		if(p1 == null) {
			throw new CustomerNotFoundException("customer not found");
		}
		p1.setUserName(customer.getUserName());
		p1.setPassword(customer.getPassword());
		p1.setEmail(customer.getEmail());
		p1.setAddress(customer.getAddress());
		p1.setContactNumber(customer.getContactNumber());
	    customerRepo.save(p1);
	    CustomerDto a=new CustomerDto();
		BeanUtils.copyProperties(p1, a);
		return a;
	}

	@Override
	public CustomerDto delCustomer(int id) throws CustomerNotFoundException {
		Customer p1 = customerRepo.findById(id).orElse(null);
		if(p1 == null) {
			throw new CustomerNotFoundException("customer not found");
		}
		customerRepo.deleteById(id);
		CustomerDto a=new CustomerDto();
		BeanUtils.copyProperties(p1, a);
		return a;
		
	}

	@Override
	public CustomerResponse viewCustomer(int id) throws CustomerNotFoundException {
		Customer p1 = customerRepo.findById(id).orElse(null);
		if(p1 == null) {
			throw new CustomerNotFoundException("customer not found");
		}
		CustomerResponse a=new CustomerResponse();
		List<Policy> policiesOfCustomer=restTemplate.getForObject("http://POLICY-SERVICE/policy/policies/"+p1.getCustomerId(),ArrayList.class );
		logger.info("{}",policiesOfCustomer);
		p1.setPolicies(policiesOfCustomer);
		List<Payment> paymentOfCustomer=restTemplate.getForObject("http://PAYMENT-SERVICE/payments/getpaymentbycustomerid/"+p1.getCustomerId(), ArrayList.class);
		logger.info("{}",paymentOfCustomer);
		p1.setPayments(paymentOfCustomer);
		BeanUtils.copyProperties(p1, a);
		return a;
	}

	@Override
	public List<CustomerRequest> viewAllCustomer() {
		List<Customer> customers=(List<Customer>) customerRepo.findAll();
		List<CustomerRequest> b=new ArrayList<>();
		for(Customer c:customers)
		{
			CustomerRequest a=new CustomerRequest();
			BeanUtils.copyProperties(c, a);
			b.add(a);
			
		}
		return b;
	}

	@Override
	public CustomerDto getCustomerByEmail(String email) {
		 Customer p1=customerRepo.findByEmail(email);
		 CustomerDto a=new CustomerDto();
			BeanUtils.copyProperties(p1, a);
			return a;
	}

	@Override
	public CustomerDto updateCustomerByEmail(String email,CustomerDto customer) throws CustomerNotFoundException {
		Customer p1 = customerRepo.findByEmail(email);
		User u1 = userRepo.findByUserName(email);
		if(p1 == null) {
			throw new CustomerNotFoundException("customer not found");
		}
		p1.setUserName(customer.getUserName());
		p1.setPassword(customer.getPassword());
		p1.setEmail(customer.getEmail());
		p1.setAddress(customer.getAddress());
		p1.setContactNumber(customer.getContactNumber());
		u1.setUserName(customer.getEmail());
		u1.setPassword(customer.getPassword());
		u1.setRole(Role.CUSTOMER);
		userRepo.save(u1);
		customerRepo.save(p1);
		CustomerDto a=new CustomerDto();
		BeanUtils.copyProperties(p1, a);
		return a;
	}

	@Override
	public CustomerDto viewCustomerByEmail(String email) {
		Customer p1 = customerRepo.findByEmail(email);
		CustomerDto a=new CustomerDto();
		BeanUtils.copyProperties(p1, a);
		return a;
		
	}
	

	@Override
	public Customer updatePasswordByEmail(String email, String password) throws CustomerNotFoundException {
		Customer p1 = customerRepo.findByEmail(email);
		User u1 = userRepo.findByUserName(email);
		if(p1 == null) {
			throw new CustomerNotFoundException("customer not found");
		}
		p1.setPassword(password);
		u1.setPassword(password);
		userRepo.save(u1);
		return customerRepo.save(p1);
	}
	

}
