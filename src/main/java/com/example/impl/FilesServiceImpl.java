package com.example.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.dao.FilesDAO;
import com.example.domain.Files;
import com.example.service.FilesService;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilesServiceImpl implements FilesService {

    private final FilesDAO filesDAO;
    private final Storage storage;

    @Transactional
    public String fileupload(List<MultipartFile> fileList, String key) {
        // 파일 키 생성 (LocalDateTime_등록자이메일)
        String fileKey = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "_" + key;

        for (MultipartFile f : fileList) {
            String originalName = f.getOriginalFilename();
            String ext = f.getContentType();
            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "_" + originalName;

            // 업로드할 버킷 이름
            String bucketName = "doctorpick";

            BlobId blobId = BlobId.of(bucketName, fileName);

            BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                    .setContentType(ext)
                    .build();

            try (ByteArrayInputStream fileStream = new ByteArrayInputStream(f.getBytes())) {
                Blob blob = storage.create(blobInfo, fileStream.readAllBytes());
                String fileUrl = blob.getMediaLink();
                Files result = new Files();
                result.setFileKey(fileKey);
                result.setOriginFileName(originalName);
                result.setFileExtension(ext);
                result.setFileName(fileName);
                result.setFilePath(fileUrl);

                filesDAO.registerFile(result);
            } catch (IOException e) {
                System.out.print("파일 저장 중 에러: " + e.getMessage());
            }
        }

        return fileKey;
    }
}
