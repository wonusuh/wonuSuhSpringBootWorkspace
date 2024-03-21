package kr.study.jpa1.controller;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.domain.StudyRecord;
import kr.study.jpa1.form.StudyForm;
import kr.study.jpa1.service.MemberService;
import kr.study.jpa1.service.StudyRecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {
    private final StudyRecordService studyService;
    private final MemberService memberService;

    @GetMapping
    public String addForm(Model model) {
        List<Member> members = memberService.members();
        model.addAttribute("members", members);
        model.addAttribute("curdate", LocalDate.now());
        model.addAttribute("curtime", LocalTime.now());
        return "/study/addForm";
    }

    @PostMapping("/record")
    public String addStudyRocord(StudyForm form) {
        log.trace("form ={}", form);
        log.trace("localDate={}, localTime={}", LocalDate.now(), LocalTime.now());

        Member member = memberService.findByLoginId(form.getMemberLoginId());
        if (member == null) {
            log.error(" { } 로그인 아이디가 존재하지 않음 ", form.getMemberLoginId());
            return "redirect:/";
        }
        studyService.record(form, member);


        return "redirect:/";
    }

    @GetMapping("/records")
    public String getAllList(Model model) {
        List<Member> members = memberService.members();
        if (members == null) {
            return "redirect:/";
        }

        List<StudyRecord> list = studyService.getAllRecords();
        if (list == null) {
            return "redirect:/";
        }
        for (StudyRecord studyRecode : list) {
            System.out.println("studyRecode = " + studyRecode);
        }
        model.addAttribute("list", list);
        return "study/list";
    }

    @DeleteMapping("/{id}")
    public @ResponseBody String deleteStudyRecord(@PathVariable Long id) {
        // 여기에 삭제 작업을 수행하는 코드를 추가합니다.
        // 예를 들어, StudyService를 사용하여 해당 id에 해당하는 레코드를 삭제할 수 있습니다.
        // deleteRecord 메서드는 예시이며, 실제로는 적절한 삭제 작업을 수행해야 합니다.
        // return 값은 클라이언트에게 보낼 응답입니다.
        System.out.println("id = " + id);
        studyService.deleteRecord(id);
        return "ok";
    }

    @GetMapping("/{keyId}")
    public String updateForm(@PathVariable Long keyId, Model model) {
        System.out.println("keyId = " + keyId);
        StudyRecord r = studyService.getOneRecord(keyId);
        log.trace("record = {}", r);
        if (r == null) {
            return "redirect:/";
        }
        model.addAttribute("curdate", LocalDate.now());
        model.addAttribute("record", r);
        return "study/updateForm";
    }

    @PostMapping("/update")
    public String updateRecord(@ModelAttribute StudyForm form, @RequestParam Long id) {
        log.trace("form={}, id={}", form, id);
        StudyRecord record = studyService.getOneRecord(id);
        log.trace("recode={}", record);
        if (record == null) {
            log.error("msg={}", "레코드 값 못찾음");
            return "redirect:/";
        }
        studyService.updateRecord(form, record);

        return "redirect:/study/records";
    }
}
