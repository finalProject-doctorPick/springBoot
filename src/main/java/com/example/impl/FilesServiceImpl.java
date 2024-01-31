package com.example.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.FilesDAO;
import com.example.domain.Files;
import com.example.service.FilesService;
import com.google.api.client.util.Value;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService{

	@Value("${spring.cloud.gcp.storage.bucket}") // application.yml에 써둔 bucket 이름
    private String bucketName;
	
	private final FilesDAO filesDAO;
	 private final Storage storage = StorageOptions.getDefaultInstance().getService();
	
	@Transactional
	public String fileupload(List<MultipartFile> fileList, String key) {
		// 파일 키 생성 (LocalDateTime_등록자이메일)
    	String fileKey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))+ "_" + key;
    	
    	for (MultipartFile f : fileList) {
    		try {
				BlobInfo blobInfo = storage.create(
						BlobInfo.newBuilder(bucketName, "")
								.setContentType(".jpg")
								.build(),
							f.getInputStream()
				);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Files result = new Files();
    		result.setFileKey(fileKey);
    		result.setFile(f);
    		filesDAO.registerFile(result);
        }
    	
		return fileKey;
	}

}
