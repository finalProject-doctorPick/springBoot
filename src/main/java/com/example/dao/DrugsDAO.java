package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dto.DrugsDTO;

@Mapper
public interface DrugsDAO {

	List<DrugsDTO> getDrugsList();

}
