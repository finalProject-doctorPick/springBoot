package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Drugs;
import com.example.dto.DrugsDTO;

@Mapper
public interface DrugsDAO {

	List<Drugs> getDrugsList(DrugsDTO dto);

}
