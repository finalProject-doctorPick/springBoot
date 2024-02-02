package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	// 리뷰 ID
	private Integer reviewId;

	// 진료 번호
	private Integer certificateNum;
	
	// 회원 번호
	private Integer memberId;

	// 의사 ID
	private Integer doctorId;
	
	// 평점
	private Integer rating;
	
	// 리뷰 내용
	private String comments;
	
	// 리뷰 제목
	private String reviewTitle;
	
	// 리뷰 작성 상태
	private String status;
	
	// 진료일
	private String certificateDate;
	
	// 병원명
	private String hospitalName;
	
	// 의사명
	private String DoctorName;
	
}
