package com.example.domain;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MemberHistory {
	private Integer certificateNum;
	private String certificateDate;
	private Integer reservationNum;
	private String description;
	private String certificateFile;
	private Integer memberId;
	private String memberName;
	private String memberBirth;
	private String memberTel;
	private Integer doctorId;
	private String reservationDate;
	private String reservationStatus;
	private String doctorEmail;
	private Integer hospitalId;
	private String doctorName;
	private String doctorBirth;
	private String doctorSex;
	private String doctorTel;
	private String doctorSubject;
	private String doctorMajor;
	private String doctorComments;
	private String doctorFile;
	private String hospitalName;
	private String hospitalAddrMain;
	private String hospitalAddrDetail;
	private String hospitalFile;
	private String hospitalLati;
	private String hospitalLong;
	private String partnershipStatus;
	private Integer paymentId;
	private LocalDateTime paymentDate;
	private String transactionType;
	private String paymentStatus;
	private String status;
	private Integer paymentAmount;
	private String originFileName;
	private String fileExtension;
	private String fileName;
	private String filePath;
}

