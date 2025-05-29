package Service;

import com.example.demo.DTO.DiaryDTO;
import com.example.demo.entity.Diary;
import com.example.demo.entity.Project;
import com.example.demo.mapper.DiaryMapper;
import com.example.demo.repository.DiaryRepository;
import com.example.demo.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final ProjectRepository projectRepository;
    public DiaryService(DiaryRepository diaryRepository, ProjectRepository projectRepository) {
        this.diaryRepository = diaryRepository;
        this.projectRepository = projectRepository;
    }

    // 전체 일기 조회
    public List<Diary> findAllDiaries() {
        return diaryRepository.findAll();
    }

    // ID로 일기 조회
    public Optional<Diary> findDiaryById(Long did) {
        return diaryRepository.findById(did);
    }

    // 프로젝트별 일기 조회
    public List<Diary> findDiariesByProject(Project project) {
        return diaryRepository.findByProject(project);
    }

    // 날짜 범위 내 일기 조회
    public List<Diary> findDiariesByDateRange(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findByDateBetween(startDate, endDate);
    }

    // 새 일기 저장
    public Diary saveDiary(Diary diary) {
        return diaryRepository.save(diary);
    }

    // 일기 삭제
    public void deleteDiary(Long did) {
        diaryRepository.deleteById(did);
    }

    public DiaryDTO.Response createDiary(DiaryDTO.Request request) {
        // 1. 프로젝트 조회
        Project project = projectRepository.findById(request.getProjectId())
                .orElseThrow(() -> new IllegalArgumentException("해당 프로젝트가 존재하지 않습니다."));

        // 2. DTO → Entity 변환
        Diary diary = DiaryMapper.dtoToEntity(request, project);

        // 3. 저장
        Diary savedDiary = diaryRepository.save(diary);

        // 4. Entity → DTO 변환해서 반환
        return DiaryMapper.entityToDto(savedDiary);
    }
}