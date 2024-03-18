package kr.boot.basic.service;

import kr.boot.basic.domain.Member;
import kr.boot.basic.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
  private final MemberRepository memberRepository;
  public MemberService(MemberRepository repository){
    this.memberRepository = repository;
  }
  // 회원가입
  public void join(Member member){
    if(validateDuplicateMember(member)){
    memberRepository.save(member);}else{
      System.out.println("이미 사용중인 아이디입니다.");
    }
  }

  // 아이디 중복 검사
  private boolean validateDuplicateMember(Member member) {
    return memberRepository.findByName(member.getName()).isEmpty();
  }

  // 전체 회원 조회
  public List<Member> findMembers(){
    return memberRepository.findAll();
  }

  // 회원 한 명 조회
  public Optional<Member> findOneMember(Long id){
    return memberRepository.findById(id);
  }
}
