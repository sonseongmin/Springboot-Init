package com.example.demo.init;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor  // JPA 기본 생성자 필수
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;
    private String uploadPath;

    private LocalDateTime uploadedAt;

    // 테스트용 데이터 생성에 쓰는 생성자
    public Document(String fileName, String uploadPath, LocalDateTime uploadedAt) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
        this.uploadedAt = uploadedAt;
    }
}
