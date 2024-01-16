package com.example.domain;

import javax.persistence.Column;

import lombok.Data;

@Data
public class Drugs {

	private String drugName;
	
	private String drugCompany;

	private String drugShape;

	private String drugColor;
	
}