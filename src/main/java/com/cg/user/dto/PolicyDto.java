package com.cg.user.dto;

import lombok.Data;

@Data
public class PolicyDto {
	private int policyId;
	private String policyName;
	private double totalAmount;
	private double termAmount;
	private double income;
	private double claimingAmount;
	
}
