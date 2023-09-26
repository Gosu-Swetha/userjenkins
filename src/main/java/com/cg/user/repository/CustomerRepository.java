package com.cg.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


import com.cg.user.entity.Customer;

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer,Integer>{
	Customer findByEmail(String email);
	


}
