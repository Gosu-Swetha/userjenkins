package com.cg.user.entity;

import lombok.Data;

@Data

public class Policy {
	private int policyId;
	private String policyName;
	private double totalAmount;
	private double termAmount;
	private double claimingAmount;
	private int customerId;
//	@ManyToOne
//	private Customer customer;
	

}
