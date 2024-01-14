package com.example.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.FilesDAO;
import com.example.domain.Files;
import com.example.service.FilesService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService{

	private final FilesDAO filesDAO;
	
	@Transactional
	public String fileupload(List<MultipartFile> fileList, String key) {
		// 파일 키 생성 (LocalDateTime_등록자이메일)
    	String fileKey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+ "_" + key;
    	
    	for (MultipartFile f : fileList) {
    		Files result = new Files();
    		result.setFileKey(fileKey);
    		result.setFile(f);
    		filesDAO.registerFile(result);
        }
    	
		return fileKey;
	}

}
