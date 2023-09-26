package com.cg.user.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;
@Entity
@Data
@Table(name="admin")
//this class is used for admin
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull(message = "id cannot be null")
	private int adminId;
	@NotEmpty(message = "email cannot be empty")
	private String email;
	@NotEmpty(message = "admin name cannot be empty")
	private String adminName;
	
	private String password;
	private String mobileNumber;
	private String userName;

	

}
