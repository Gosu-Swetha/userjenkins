package com.cg.user.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.user.dto.AdminCredentials;
import com.cg.user.dto.AdminDto;
import com.cg.user.entity.Admin;
import com.cg.user.entity.Role;
import com.cg.user.entity.User;

import com.cg.user.exceptions.AdminNotExistException;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.InValidEmailException;
import com.cg.user.repository.AdminRepository;
import com.cg.user.repository.UserRepository;
import com.cg.user.services.AdminService;

@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepo;
	

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;

	public Admin insertAdmin(AdminDto admin) throws CustomerAlreadyExistsException {                  //Method for inserting Admin object in repository
		Admin a1 = new Admin();
		a1.setAdminName(admin.getAdminName());
		a1.setPassword(admin.getPassword());
		a1.setUserName(admin.getUserName());
		String regex = "^(.+)@(.+)$";                          //validation for email
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(admin.getEmail());
		String phNo = "^[0-9]{10}$";                          //validation for mobileNumber
		Pattern ptrn = Pattern.compile(phNo);
		Matcher m2 = ptrn.matcher(admin.getMobileNumber()); 
		if(matcher.matches()==true && m2.matches()==true) {    //checks condition if pattern matched or not
			a1.setEmail(admin.getEmail());
			a1.setMobileNumber(admin.getMobileNumber());
			User u1 = new User();
			u1.setUserName(admin.getUserName());
			u1.setPassword(admin.getPassword());
			u1.setRole(Role.ADMIN);
			userService.userSignUp(u1);
			return adminRepo.save(a1);
		}else {
			throw new InValidEmailException("Invalid Email or Phone Number"); //else throw exception with message
		}
	}

	@Override
	public Admin updateAdmin(int id, AdminDto admin) {                   //Method for updating Admin object by primary key
		Optional<Admin> a1 = adminRepo.findById(id);
		if(a1.isPresent()) {
			Admin a2 = a1.get();
			a2.setAdminName(admin.getAdminName());
			a2.setEmail(admin.getEmail());
			a2.setPassword(admin.getPassword());
			a2.setMobileNumber(admin.getMobileNumber());
			return adminRepo.save(a2);
		}else{
			throw new AdminNotExistException("Admin Not Found");
		}
	}

	@Override
	public Admin delAdmin(int id) throws AdminNotExistException{                            //Method for deleting Admin object from repository
		Optional<Admin> a1 = adminRepo.findById(id);
		if(a1.isPresent()) {
			adminRepo.deleteById(id);
			return a1.get();
		}
		throw new AdminNotExistException("Admin not Found");
	}

	

	
	@Override
	public Admin getAdmin(int id) throws AdminNotExistException{                         //Method for getting Admin object by id
		Admin a1 = adminRepo.findById(id).orElse(null);
		return a1;
	}

	@Override
	public Admin getAdmin(String email) {                    //Method for getting Admin object by email
		Admin a1 = adminRepo.findByUserName(email);
		return a1;
	}

	@Override
	public Admin updateAdminByEmail(String email, AdminDto admin) {     //Method for updating Admin entity by email
		Admin a1 = adminRepo.findByUserName(email);
		User u1 = userRepo.findByUserName(email); 
		if(a1 != null) {
			a1.setAdminName(admin.getAdminName());
			a1.setEmail(admin.getEmail());
			a1.setPassword(admin.getPassword());
			a1.setMobileNumber(admin.getMobileNumber());
			u1.setUserName(admin.getAdminName());
			u1.setPassword(admin.getPassword());
			u1.setRole(Role.ADMIN);
			userRepo.save(u1);
			return adminRepo.save(a1);
		}else{
			throw new AdminNotExistException("Admin Not Found");
		}
	}

	@Override
	public AdminCredentials getcredentials(String userName) {
		Admin a1 = adminRepo.findByUserName(userName);
		AdminCredentials a2=new AdminCredentials();
		a2.setUserName(a1.getUserName());
		a2.setPassword(a1.getPassword());;
		return a2;
		
	}

	

	
//	@Override
//	public AdminDto addAdmin(AdminDto admin)throws DuplicateAdminException { 
//		
//		Optional<Admin> a= adminRepository.findById(admin.getAdminId());
//		if(a.isPresent())
//		{
//			throw new AdminException("Id is exsisting");
//		}
//		else
//		{
//
//			Admin c=new Admin();
//			BeanUtils.copyProperties(admin,c);
//			adminRepository.save(c);
//		
//		return admin;
//		}
//	}
//
//	
//	@Override
//	public 	AdminDto updateAdmin(AdminDto admin) throws AdminException {     
//		
//		Optional<Admin> a=adminRepository.findById(admin.getAdminId());
//		if(a.isPresent())
//		{
//			Admin c=new Admin();
//			BeanUtils.copyProperties(admin, c);
//			adminRepository.save(c);
//			return admin;
//		}
//		else
//		{
//			throw new AdminException("Id is not exsisting");
//		
//		}
//	}
//
//	
//	@Override
//	public AdminDto removeAdmin(int adminId)throws AdminException {      
//		if(adminRepository.existsById(adminId)) {
//		Admin admin = adminRepository.findById(adminId).get();
//		adminRepository.deleteById(adminId);
//		AdminDto a=new AdminDto();
//		BeanUtils.copyProperties(admin, a);
//		return a;
//		}
//		else {
//			throw new AdminException("Admin Id not found");
//		}
//	}
//	
//	
//
//	@Override
//	public AdminDto viewAdmin(int adminId) throws AdminException {      
//		
//		if(adminRepository.existsById(adminId))
//		{
//			Admin c=adminRepository.findById(adminId).get();
//			AdminDto d=new AdminDto();
//			BeanUtils.copyProperties(c, d);
//			return d;
//		}
//		else
//		{
//			throw new AdminException("There are no admin having id:" + adminId);
//		}
//		
//	
//	}
//
//	@Override
//	public List<AdminDto> getAllAdmins() {
//		
//		List<Admin> b = adminRepository.findAll();
//		List<AdminDto> e=new ArrayList<>();
//		for(Admin admin:b)
//		{
//			AdminDto d=new AdminDto();
//			BeanUtils.copyProperties(admin, d);
//			e.add(d);
//		}
//	
//		
//		
//		return e;
//	}


		
	
}
