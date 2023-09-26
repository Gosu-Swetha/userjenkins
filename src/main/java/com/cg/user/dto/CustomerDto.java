package com.cg.user.dto;

import java.time.LocalDate;

import java.util.ArrayList;

import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomerDto {
//	private int customerId;
	@NotBlank(message = "user name cannot be null")
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String dob;
	private String contactNumber;
	private String gender;
	private int income;
	private String address;
	private String employment;
	
		

}
