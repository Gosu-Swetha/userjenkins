package com.cg.user.entity;



import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;



@Data
@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull(message = "UserId cannot be null")
	private int userId;
	@NotBlank(message = "username cannot be blank")
	private String userName;
	@NotBlank(message = "password cannot be blank")
	private String password;
	@Enumerated(EnumType.STRING)
	private Role role;
		
	
	
	
	

}
