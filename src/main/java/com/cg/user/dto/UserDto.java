package com.cg.user.dto;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.Data;

@Data

public class UserDto {
	private int userId;
	private String userName;
	private String password;
	@Enumerated(EnumType.STRING)
	private String role;

	
	
	

}
