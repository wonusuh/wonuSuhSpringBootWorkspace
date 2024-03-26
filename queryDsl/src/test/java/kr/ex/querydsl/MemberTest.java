package kr.ex.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.ex.querydsl.entity.Member;
import kr.ex.querydsl.entity.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static kr.ex.querydsl.entity.QMember.member;
import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
class MemberTest {
    @Autowired
    EntityManager em;
    JPAQueryFactory query;

    @BeforeEach
    void initData() {
        // 쿼리DSL 객체
        query = new JPAQueryFactory(em);

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        // 영속성 컨텍스트 초기화
        em.flush();
        em.clear();
        System.out.println("==========================");
    }

    @Test
    void testDomain() {
        Member member5 = new Member("member5", 10);
        em.persist(member5);

        List<Member> list = query.selectFrom(member).fetch();
        assertThat(list.size()).isEqualTo(5);
    }

    @Test
    void searchByJPQL() {
        Member findMember = em.createQuery("select m from Member m where m.username=:username", Member.class)
                .setParameter("username", "member1").getSingleResult();

        assertThat(findMember.getUsername()).isEqualTo("member1");
    }

    @Test
    void searchByQueryDsl() {
        Member findMember = query.selectFrom(member)
                .where(member.username.eq("member1"))
                .fetchOne();
        assertThat(findMember.getUsername()).isEqualTo("member1");
    }


}