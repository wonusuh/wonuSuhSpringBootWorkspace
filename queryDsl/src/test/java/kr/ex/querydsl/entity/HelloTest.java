package kr.ex.querydsl.entity;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class HelloTest {
    @Autowired
    EntityManager em;

    @Test
    void firstTest() {
        Hello hello = new Hello();
        em.persist(hello);
    }
}