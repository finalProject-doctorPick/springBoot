package com.example.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.domain.Hospital;

@Mapper
public interface HospitalDAO {

    List<Hospital> getHospitalList();
}
