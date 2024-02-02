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
	
	public void setDoctorTel(String doctorTel) {
        if (doctorTel.length() == 8) {
            this.doctorTel = doctorTel.substring(0, 4) + "-" + doctorTel.substring(4);
        } else if (doctorTel.length() == 9) {
            this.doctorTel = doctorTel.substring(0, 2) + "-" + doctorTel.substring(2, 5) + "-" + doctorTel.substring(5);
        } else if (doctorTel.length() == 10) {
            this.doctorTel = doctorTel.substring(0, 3) + "-" + doctorTel.substring(3, 6) + "-" + doctorTel.substring(6);
        } else if (doctorTel.length() == 11) {
            this.doctorTel = doctorTel.substring(0, 3) + "-" + doctorTel.substring(3, 7) + "-" + doctorTel.substring(7);
        } else {
            this.doctorTel = doctorTel;
        }
    }
	
	public void setMemberTel(String memberTel) {
		if (memberTel.length() == 8) {
			this.memberTel = memberTel.substring(0, 4) + "-" + memberTel.substring(4);
		} else if (memberTel.length() == 9) {
			this.memberTel = memberTel.substring(0, 2) + "-" + memberTel.substring(2, 5) + "-" + memberTel.substring(5);
		} else if (memberTel.length() == 10) {
			this.memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 6) + "-" + memberTel.substring(6);
		} else if (memberTel.length() == 11) {
			this.memberTel = memberTel.substring(0, 3) + "-" + memberTel.substring(3, 7) + "-" + memberTel.substring(7);
		} else {
			this.memberTel = memberTel;
		}
	}
}

