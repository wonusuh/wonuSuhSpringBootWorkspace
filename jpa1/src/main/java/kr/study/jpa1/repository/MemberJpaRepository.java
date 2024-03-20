package kr.study.jpa1.repository;

import jakarta.persistence.EntityManager;
import kr.study.jpa1.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class MemberJpaRepository implements MemberRepository {
    private final EntityManager em;

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = em.createQuery("select m from Member m", Member.class)
                .getResultList();
        return members;
    }

    @Override
    public Member findById(Long id) {
        return null;
    }

    @Override
    public Member findByLoginId(String loginId) {
        List<Member> members = em.createQuery("select m from Member m where m.loginId=:loginId", Member.class)
                .setParameter("loginId", loginId)
                .getResultList();
        // return members == null? null : members.get(0);
        return members.stream().findAny().orElse(null); // 객체가 있으면 객체를 반환 아니면 null 리턴
    }

    @Override
    public void deleteById(Long id) {
        int cnt = em.createQuery("delete from Member m where m.id=:id")
                .setParameter("id", id)
                .executeUpdate();
        if (cnt == 0) {
            log.error("msg ={}", "삭제 실패");
        }
    }
}
