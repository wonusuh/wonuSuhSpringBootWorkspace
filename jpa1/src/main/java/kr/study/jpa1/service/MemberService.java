package kr.study.jpa1.service;


import kr.study.jpa1.domain.Member;
import kr.study.jpa1.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true) // 읽기전용 트렌젝셔널 -> sql 저장소 빠진거
public class MemberService {
    private final MemberJpaRepository memberRepository;

    @Transactional // 읽기 , 쓰기(삭제, 수정 )
    public Long join(Member member) throws IllegalStateException {
        validateMemberId(member);
        Member m = memberRepository.save(member);
        log.trace("savedmember ={}", m);
        return m.getId();
    }

    private void validateMemberId(Member member) throws IllegalStateException {
        if (memberRepository.findByLoginId(member.getLoginId()) != null) {

            throw new IllegalStateException("이미 존재하는 회원 아이디가 있습니다");
        }
    }

    public List<Member> members() {
        return memberRepository.findAll();
    }

    @Transactional
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}