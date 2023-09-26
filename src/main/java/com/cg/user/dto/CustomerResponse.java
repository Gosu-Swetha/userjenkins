package com.cg.user.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Transient;

import com.cg.user.entity.Payment;
import com.cg.user.entity.Policy;

import lombok.Data;
@Data
public class CustomerResponse {
	
	
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
	private int customerId;
	@Transient
	private List<Policy> policies=new ArrayList<>();

	@Transient
	private List<Payment> payments=new ArrayList<>();

	public CustomerResponse() {
		super();
	}

	

	







	public List<Payment> getPayments() {
		return payments;
	}











	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}











	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmployment() {
		return employment;
	}

	public void setEmployment(String employment) {
		this.employment = employment;
	}

	public CustomerResponse(int customerId, List<Policy> policies) {
		super();
		this.customerId = customerId;
		this.policies = policies;
	}











	public int getCustomerId() {
		return customerId;
	}











	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}











	public List<Policy> getPolicies() {
		return policies;
	}

	public void setPolicies(List<Policy> policies) {
		this.policies = policies;
	}

	
}
