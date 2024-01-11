package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorSignupDTO {
	
	private String doctorEmail;

	private String doctorPwd;

	private String doctorName;

	private String doctorBirth;

	private String doctorSex;

	private String doctorAddrMain;

	private String doctorAddrDetail;

	private String fileKey;

	private String doctorJoinDate;

	private String doctorConfirmYn;
}
