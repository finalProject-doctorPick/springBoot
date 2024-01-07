package com.example.dto;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class HospitalSearchResponseDTO {
	@Column(name = "hospital_name")
	private String hospitalName;

	@Column(name = "hospital_addr_main")
	private String hospitalAddrMain;

	@Column(name = "hospital_addr_detail")
	private String hospitalAddrDetail;

	@Column(name = "partnership_status")
	private String partnershipStatus;
}
