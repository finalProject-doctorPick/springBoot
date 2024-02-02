package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorAvail {
	
	/* 진료 시간 */
	
	// 의사 영업시간 ID
	private Integer doctorAvailId;
	
	// 의사 ID
	private Integer doctorId;
	
	// 의사 이메일
	private String doctorEmail;
	
	// 영업 요일
	private String day;
	
	// 영업시작시간
	private String starttime;
	
	// 영업종료시간
	private String endtime;
	
	// 휴식시작시간
	private String restStarttime;
	
	// 휴식종료시간
	private String restEndtime;

	// 근무/휴무 여부 (근무일 경우 'Y', 휴무일 경우 'N')
	private String useStatus;
	
}
