package com.example.demo.init;

import com.example.demo.domain.Document;
import com.example.demo.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Profile("dev")  // dev 환경에서만 실행되도록 설정!
@Component
@RequiredArgsConstructor
public class TestDataLoader implements CommandLineRunner {

    private final DocumentRepository documentRepository;
    public Document(String fileName, String uploadPath, LocalDateTime uploadedAt) {
        this.fileName = fileName;
        this.uploadPath = uploadPath;
        this.uploadedAt = uploadedAt;
    }

    @Override
    public void run(String... args) {
        Document doc1 = new Document("test_doc_1.pdf", "/uploads", LocalDateTime.now());
        Document doc2 = new Document("sample_form.pdf", "/test", LocalDateTime.now());

        documentRepository.save(doc1);
        documentRepository.save(doc2);

        System.out.println("✅ 초기 테스트용 Document 데이터 삽입 완료!");
    }
}
