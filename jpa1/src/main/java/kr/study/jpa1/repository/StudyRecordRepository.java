package kr.study.jpa1.repository;

import kr.study.jpa1.domain.StudyRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudyRecordRepository extends JpaRepository<StudyRecord, Long> {
}
