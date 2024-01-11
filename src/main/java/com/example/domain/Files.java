package com.example.domain;

import java.io.File;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Files {
    String fileKey;
    String originFilename;
    String filename;
    String filepath;

    MultipartFile file;
    private String r_img;
    private String r_rimg;

    // 기본 생성자 추가
    public Files() {
    }

    // 파일 설정 메서드
    public void setFile(MultipartFile file) {
        this.file = file;
        
        // 업로드 파일이 있는 경우
        if (!file.isEmpty()) {
        	this.setOriginFilename(file.getOriginalFilename());
            this.r_img = file.getOriginalFilename();

            // 실제 저장된 파일명 만들기
            UUID uuid = UUID.randomUUID();
            this.r_rimg = uuid.toString() + "_" + r_img;
            this.filename = r_rimg;

            // 실제 파일 저장
            // 나중에 웹서버 경로를 찾아서 수정
            String path =  "C:\\imgFolder\\";
            this.filepath= path;
            File f = new File(path + r_rimg);

            try {
                file.transferTo(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
