package kr.basic.security.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private RoleUser role;
    // private Timestamp createDate;
    //OAuth 를 위해 추가하는 필드
    private String provider;
    private String providerId;
    @Builder
    public Users(String username, String password, String email, String provider, String providerId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.provider = provider;
        this.providerId = providerId;
        this.role = RoleUser.ROLE_USER;
    }

}
