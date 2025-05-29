package com.example.demo.init;

import com.example.demo.init.Document;
import com.example.demo.init.DocumentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DocumentRepositoryTest {

    @Autowired
    private DocumentRepository documentRepository;

    @Test
    void 문서저장_조회_테스트() {
        Document doc = new Document("sample.pdf", "/uploads", LocalDateTime.now());
        Document saved = documentRepository.save(doc);

        Optional<Document> found = documentRepository.findById(saved.getId());
        assertTrue(found.isPresent());
        assertEquals("sample.pdf", found.get().getFileName());
    }
}
