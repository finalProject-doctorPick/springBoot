package com.example.domain;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

import java.io.File;
import java.util.UUID;

@Data
public class Files {
    private String fileKey;
    private String originFileName;
    private String fileName;
    private String filePath;

    private MultipartFile file;
    private String r_img;
    private String r_rimg;

    // 내 PC의 IP 주소와 포트
    private static final String MY_PC_IP_ADDRESS = "118.217.203.50";
    private static final String MY_PC_PORT = "8080";

    // 파일 설정 메서드
    // 파일 설정 메서드
    public void setFile(MultipartFile file) {
        this.file = file;

        // 업로드 파일이 있는 경우
        if (!file.isEmpty()) {
            this.setOriginFileName(file.getOriginalFilename());
            this.r_img = file.getOriginalFilename();

            // 실제 저장된 파일명 만들기
            UUID uuid = UUID.randomUUID();
            this.r_rimg = uuid.toString() + "_" + r_img;
            this.fileName = r_rimg;

            // 내 PC에 저장
            String localPath = "C:\\imgFolder\\";
            this.filePath = localPath;

            File directory = new File(localPath);
            if (!directory.exists()) {
                boolean created = directory.mkdirs();
                if (!created) {
                    throw new RuntimeException("Failed to create directory: " + localPath);
                }
            }

            File f = new File(localPath + r_rimg);

            try {
                file.transferTo(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}