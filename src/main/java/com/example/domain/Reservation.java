package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
	
	//에약 id번호
	private Integer reservation_num;
	
	//에약회원id번호
	private Integer  member_id;
	
	//예약회원id번호
	private Integer doctor_id;
	
	//예약 진료일
	private String reservation_date;
	
	//예약 상태
	private String reservation_status;

}
