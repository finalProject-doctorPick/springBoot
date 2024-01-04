package com.example.domain;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data		// lombok
@Entity		// JPA가 되는 객체 (VO != DTO != Entity)
@Table(name="board")
public class BoardVO {

	// 글번호
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seq;
	
	// 글제목
	private String title;
	
	// 작성자
	private String writer;
	
	// 내용
	private String content;
	
	// 작성일
	@Column(columnDefinition = "date default (current_time)", nullable = true, insertable = false, updatable = false)
	private Date regdate;
	
	// 조회수
	@Column(columnDefinition = "integer default 0", insertable = false)
	private Integer cnt;
}
