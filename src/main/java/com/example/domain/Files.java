package com.example.domain;

import java.io.File;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Files {
    private String fileKey;
    private String originFileName;
    private String fileName;
    private String filePath;

    private MultipartFile file;
    private String r_img;
    private String r_rimg;

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

            // 실제 파일 저장
            // 나중에 웹서버 경로를 찾아서 수정
            String path =  "C:\\imgFolder\\";
            this.filePath= path;
            File f = new File(path + r_rimg);

            try {
                file.transferTo(f);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
