package com.cg.user.controller;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.user.dto.AdminCredentials;
import com.cg.user.dto.AdminDto;
import com.cg.user.dto.UserDto;
import com.cg.user.entity.Admin;
import com.cg.user.exceptions.AdminNotExistException;
import com.cg.user.exceptions.CustomerAlreadyExistsException;
import com.cg.user.exceptions.InValidEmailException;
import com.cg.user.repository.AdminRepository;
import com.cg.user.services.AdminService;


@RestController
@RequestMapping("/user/admin")
@CrossOrigin("*")
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	
	private static Logger logger = LogManager.getLogger();        //To log messages in the console 
	
	@PostMapping("/insertAdmin")
	public ResponseEntity<Admin> addAdmin(@RequestBody AdminDto admin) throws InValidEmailException, CustomerAlreadyExistsException {         //Controller to add admin object using @PostMapping
		logger.info("Sending Request to add admin");
		Admin a1 = adminService.insertAdmin(admin);
		logger.info("Admin Added");
		return new ResponseEntity<>(a1,HttpStatus.CREATED);
	}
	 
	@GetMapping("/getAdmin/{id}")                                                //Controller to get admin object using id as @PathVariable and @GetMapping
	public ResponseEntity<Admin> getAdmin(@PathVariable("id")int id)throws AdminNotExistException {                  
		logger.info("Sending Request to get admin");
		Admin a1 = adminService.getAdmin(id);
		logger.info("Admin recieved");
		return new ResponseEntity<>(a1,HttpStatus.CREATED);
	}
	
//	@GetMapping("/getAdminByEmail/{email}")                          
//	public ResponseEntity<Admin> getAdmin(@PathVariable("email")String email) {      //Controller to get admin object using email as @PathVariable and @GetMapping
//		logger.info("Sending Request to get admin");
//		Admin a1 = adminService.getAdmin(email);
//		logger.info("Admin recieved");
//		return new ResponseEntity<>(a1,HttpStatus.CREATED);
//	}
	
	
	
	@PutMapping("/updateAdmin/{id}")                                                  //Controller to update admin object using id as @PathVariable and @PutMapping
	public ResponseEntity<Admin> updateAdmin(@PathVariable("id")int id, @RequestBody AdminDto admin) {
		logger.info("Sending request to update admin");
		Admin a1 = adminService.updateAdmin(id,admin);
		logger.info("Admin updated");
		return new ResponseEntity<>(a1,HttpStatus.OK);
	}
	
	@PutMapping("/updateAdminByEmail/{email}")                                   //Controller to update admin object using email as @PathVariable and @PutMapping
	public ResponseEntity<Admin> updateAdminByEmail(@PathVariable("email")String email, @RequestBody AdminDto admin) {
		logger.info("Sending request to update admin by email");
		Admin a1 = adminService.updateAdminByEmail(email,admin);
		logger.info("Admin updated");
		return new ResponseEntity<>(a1,HttpStatus.OK);
	}
	
	
	
	@DeleteMapping("/deleteAdmin/{id}")                              //Controller to delete admin object using id as @PathVariable and @DeleteMapping
	public ResponseEntity<Admin> deleteAdmin(@PathVariable("id")int id)throws AdminNotExistException{
		logger.info("Sending request to delete admin");
		Admin a1 = adminService.delAdmin(id);
		logger.info("Admin deleted");
		return new ResponseEntity<>(a1,HttpStatus.OK);
	}
	
	@GetMapping("credentials/{userName}")
	public ResponseEntity<AdminCredentials> getcredentials(@PathVariable ("userName") String userName) {
		return new ResponseEntity<>(adminService.getcredentials(userName),HttpStatus.OK);
	}
	
	
    @Autowired
    AdminRepository adminRepository;
	
//
//
//  @PostMapping("/addAdmin")
//   public ResponseEntity<AdminDto> addAdmin(@RequestBody AdminDto admin) throws DuplicateAdminException {
//	return new ResponseEntity<>(adminService.addAdmin(admin),HttpStatus.OK);
//}
//	
//	@PutMapping("/updateAdmin")
//	public ResponseEntity<AdminDto> updateAdmin(@RequestBody AdminDto admin) throws AdminException {
//		return new ResponseEntity<>(adminService.updateAdmin(admin),HttpStatus.OK);
//	}
//	
//	@GetMapping("/viewAdmin/{id}")
//	public ResponseEntity<AdminDto> viewAdmin(@PathVariable("id") int adminId)throws AdminException  {
//		return new ResponseEntity<>(adminService.viewAdmin(adminId),HttpStatus.OK);
//	}
//	@DeleteMapping("/deleteAdmin/{id}")
//	public ResponseEntity<AdminDto> removeAdmin(@PathVariable("id") int adminId) throws AdminException{
//		return new ResponseEntity<>(adminService.removeAdmin(adminId),HttpStatus.OK);
//	}
//
//	@GetMapping("/getAllAdmins")
//	public ResponseEntity<List<AdminDto>> getAllAdmins(){
//		return new ResponseEntity<>(adminService.getAllAdmins(),HttpStatus.OK);
//	}
//	
	@GetMapping("/getAllAdmins")
	public List<Admin> getAllAdmins(){
		return adminRepository.findAll();
		
	}
	
	
}
