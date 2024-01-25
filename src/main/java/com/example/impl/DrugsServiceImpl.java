package com.example.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.DrugsDAO;
import com.example.domain.Drugs;
import com.example.dto.DrugsDTO;
import com.example.service.DrugsService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class DrugsServiceImpl implements DrugsService {
   
   @Autowired
   private DrugsDAO drugsDAO;

   @Override
   public List<Drugs> getDrugsList(DrugsDTO dto) {
      List<Drugs> d = drugsDAO.getDrugsList(dto);
      return d;
   } 

}