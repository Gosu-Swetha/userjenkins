package com.cg.user.entity;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Data
@NoArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int customerId;
	@NotBlank(message = "userName Cannot be Null")
	private String userName;
	@NotBlank(message = "firstname cannot be blank")
	private String firstName;
	@NotBlank(message = "lastname cannot be blank")
	private String lastName;
	@NotBlank(message = "email cannot be blank")
	private String email;
	@NotBlank(message = "password cannot be blank")
	private String password;
	@NotBlank(message = "date cannot be blank")
	private String dob;
	@NotBlank(message = "contactnumber cannot be blank")
	private String contactNumber;
	@NotBlank(message = "gender cannot be blank")
	private String gender;
	@NotNull(message = "income cannot be blank")
	private int income;
	@NotBlank(message = "adddress cannot be blank")
	private String address;
	@NotBlank(message = "employment cannot be blank")
	private String employment;
	
	
	
	
	   @Transient
		private List<Policy> policies=new ArrayList<>();
		
		@Transient
		private List<Payment> payments=new ArrayList<>();

		

	
//		@Transient 
//		private User user;

	
	
}
