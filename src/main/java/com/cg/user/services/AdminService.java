package com.cg.user.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.cg.user.dto.AdminCredentials;
import com.cg.user.dto.AdminDto;
import com.cg.user.entity.Admin;

import com.cg.user.exceptions.AdminNotExistException;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.InValidEmailException;

public interface AdminService {
//	public List<AdminDto> getAllAdmins();
//	public AdminDto addAdmin(AdminDto admin) throws DuplicateAdminException;
//	public AdminDto updateAdmin(AdminDto admin) throws AdminException ;
//    public AdminDto removeAdmin(int adminId) throws AdminException;
//    public AdminDto viewAdmin(int adminId) throws AdminException;
    Admin insertAdmin(AdminDto admin) throws InValidEmailException, CustomerAlreadyExistsException;

	Admin updateAdmin(int id, AdminDto admin)throws AdminNotExistException;

	Admin delAdmin(int id)throws AdminNotExistException;

	
	Admin getAdmin(int id)throws AdminNotExistException;

	Admin getAdmin(String email);

	Admin updateAdminByEmail(String email, AdminDto admin);
	
	AdminCredentials getcredentials(String userName);
	
	
	
}




















