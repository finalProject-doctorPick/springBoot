package com.example.domain;

import lombok.Data;

@Data
public class DrugstoreHistory {

	private Integer drugstorHistoryId;
	private Integer drugstoreId;
	private Integer certificateNum;
	private Integer memberId;
	private String memberName;
	private String memberBirth;
	private Integer doctorId;
	private String receiveType;
	private String remarks;
	private Integer paymentAmount;
	private String paymentStatus;
	private String status;
}
