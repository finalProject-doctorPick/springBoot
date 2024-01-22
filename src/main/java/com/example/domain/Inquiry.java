package com.example.domain;

import lombok.Data;

@Data
public class Inquiry {
	private Integer inquiryId;
	private String inquiryType;
	private String inquiryTitle;
	private String inquiryWriterEmail;
	private String inquiryComments;
	private String inquiryRegdate;
	private String inquiryAnswer;
	private String inquiryAnswerRegdate;
	private String status;
}
