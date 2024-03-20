package kr.study.jpa1.controller;

import kr.study.jpa1.domain.Member;
import kr.study.jpa1.form.MemberForm;
import kr.study.jpa1.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String joinForm() {
        return "member/joinForm";
    }

    @PostMapping
    public String joinMember(@ModelAttribute MemberForm form) {
        //System.out.println("memberForm = " + memberForm);
        log.trace("memberForm ={}", form);
        Member member = Member.builder()
                .password(form.getPw())
                .loginId(form.getId())
                .name(form.getName())
                .build();

        try {
            memberService.join(member);
            log.trace("member ={}", member);
        } catch (Exception e) {
            log.error("errMSG={}", e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/members")
    public String members(Model model) {
        List<Member> members = memberService.members();
        members.forEach(m -> System.out.println(m));
        model.addAttribute("members", members);
        return "/member/members";
    }// 회원 삭제를 처리하는 메서드

    @GetMapping("/{keyId}")
    public String deleteMember(@PathVariable Long keyId) {
        // 여기에 회원 삭제 로직을 작성합니다.
        // id를 사용하여 데이터베이스에서 회원을 찾고 삭제합니다.
        // 예를 들어, MemberService를 통해 회원을 삭제하는 메서드를 호출할 수 있습니다.
        System.out.println(keyId);
        memberService.deleteMember(keyId);
        return "redirect:/member/members";
    }

    @DeleteMapping("/{keyId}")
    public @ResponseBody String deleteMemberAjax(@PathVariable Long keyId) {

        return "ok";
    }
}
