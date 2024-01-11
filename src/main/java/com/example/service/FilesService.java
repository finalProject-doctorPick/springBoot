package com.example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FilesService {

	// 파일 업로드
	String fileupload(List<MultipartFile> fileList, String key);
}
