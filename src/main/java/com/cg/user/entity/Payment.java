package com.cg.user.entity;

import java.util.Date;

import lombok.Data;
@Data
public class Payment {
	private int paymentId;
	private int amountPaid;
	private int policyId;
	private String paymentStatus;
	private Date paymentDate;
	private int customerId;
	
	

}
