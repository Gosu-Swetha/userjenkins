package com.cg.user.dto;

import java.util.Date;

import lombok.Data;
@Data
public class PaymentDto {
	private int paymentId;
	private int amountPaid;
	private int policyId;
	private String paymentStatus;
	private Date paymentDate;
	private int userId;
	


}
