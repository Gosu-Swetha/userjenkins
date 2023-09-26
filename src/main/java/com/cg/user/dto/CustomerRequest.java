package com.cg.user.dto;

import lombok.Data;
@Data
public class CustomerRequest {
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String dob;
	private String contactNumber;
	private String gender;
	private int income;
	private String address;
	private String employment;
//	@Transient
//	private List<Policy> policies=new ArrayList<>();
//	
//	@Transient
//	private List<Payment> payments=new ArrayList<>();
	
	
}
