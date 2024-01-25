package com.example.domain;

import java.time.LocalDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	// 리뷰 ID
	private Integer reviewId;

	// 예약 번호
	private Integer reservationNum;
	
	// 회원 번호
	private Integer memberId;

	// 의사 ID
	private Integer doctorId;
	
	// 평점
	private Integer rating;
	
	// 리뷰 내용
	private String comments;
	
	// 리뷰 작성 상태
	private String status;
	
	// 진료일
	private String certificateDate;
	// 병원명
	private String hospitalName;
	// 의사명
	private String DoctorName;
	
}
