package com.example.domain;

import lombok.Data;

@Data
public class DrugstoreHistory {

	private Integer drugstoreHistoryId;
	private Integer drugstoreId;
	private Integer certificateNum;
	private Integer memberId;
	private String memberName;
	private String memberBirth;
	private String memberTel;
	private Integer doctorId;
	private String receiveType;
	private String remarks;
	private Integer paymentAmount;
	private String paymentStatus;
	private String status;
	private String fileKey;
	
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
	
	private String receiveTypeDescription;
	private String paymentStatusDescription;
}
