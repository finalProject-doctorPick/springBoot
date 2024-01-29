package com.example.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "reservation")
public class ReservationEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "reservation_num")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer reservationNum;
	
	@Column(name = "member_id")
	private Integer memberId;

	@Column(name = "doctor_id")
	private Integer doctorId;

	@Column(name = "reservation_date")
	private String reservationDate;

	@Column(name = "reservation_status")
	private String reservationStatus;
	
	@Column(name = "reservation_payment")
	private String reservationPayment;
	
	@Column(name = "file_key")
	private String fileKey;
	
	@Column(name = "patient_comments")
	private String patientComments;
	
	@ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "member_id", insertable = false, updatable = false)
    private MemberEntity members;
}
