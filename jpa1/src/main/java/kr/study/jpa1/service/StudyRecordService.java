package kr.study.jpa1.service;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecord;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.repository.StudyRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudyRecordService {
    private final StudyRecordRepository studyRepository;

    public List<StudyRecord> recordList() {
        return studyRepository.findAll();
    }

    @Transactional
    public void record(StudyForm form, Member member) {
        StudyRecord re = StudyRecord.createRecord(form, member);
        studyRepository.save(re);
    }

    public List<StudyRecord> getAllRecords() {
        return studyRepository.selectAll();
    }

    @Transactional
    public void deleteRecord(Long id) {
        System.out.println("id ======================= " + id);
        studyRepository.deleteById(id);
    }

    public StudyRecord getOneRecord(Long id) {
        Optional<StudyRecord> recode = studyRepository.findById(id);
        return recode.isPresent() ? recode.get() : null;
    }

    @Transactional
    public void updateRecord(StudyForm form, StudyRecord re) {
        StudyRecord updateRecord = StudyRecord.modifyRecord(form, re);
        studyRepository.save(updateRecord);
    }

    @Transactional
    public void deleteAllRecordsByMember(Member m) {
        List<StudyRecord> list = studyRepository.searchStudyRecordByMemberId(m.getId());
        if (list != null) {
            list.forEach((r) -> {
                studyRepository.deleteByMember(r.getMember());
            });
        }
    }
}
