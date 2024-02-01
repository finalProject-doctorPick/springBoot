package com.example.domain;

import lombok.Data;

@Data
public class Certificate {
	private Integer certificateNum;
	private Integer reservationNum;
	private String certificateDate;
	private Integer memberId;
	private String memberName;
	private Integer doctorId;
	private String doctorName;
	private Integer amount;
	private String description;
	private String fileKey;
	private String 	originFileName;
	private String 	fileExtension;
	private String 	fileName;
	private String 	filePath;
}
