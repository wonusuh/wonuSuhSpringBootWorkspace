package kr.study.jpa1.form;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder // 생성할 때만 초기값 넣을 수 있음
public class StudyForm {
    private String memberId;
    private String studyDay; // 현재나 과거만 선택 가능하게
    private String startTime; // 오후 6:10 이라면
    private int studyMins; // 40이 들어오면
    private String contents;
}
