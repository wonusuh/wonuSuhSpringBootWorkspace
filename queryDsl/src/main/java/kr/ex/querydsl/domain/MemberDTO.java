package kr.ex.querydsl.domain;

import lombok.Data;

@Data
public class MemberDTO {
    private String username;
    private int age;

    public MemberDTO() {
    }

    public MemberDTO(String username, int age) {
        this.username = username;
        this.age = age;
    }
}