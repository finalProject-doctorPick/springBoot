package com.example.dto;

import javax.persistence.Column;

import lombok.Data;

@Data
public class DrugsDTO {
	
	@Column(name = "drug_front")
	private String drugFront;
	
	@Column(name = "drug_back")
	private String drugBack;
	
	@Column(name = "drug_color")
	private String drugColor;
}
