package com.basic.myboard.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> {
            web.ignoring().requestMatchers(new String[]{"/favicon.ico", "/resources/**", "/error"});
        };
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/user/**").authenticated() // 모든회원이 접근 가능
                        .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN") // 매니저 이상만 접근가능
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN") // 관리자만 접근가능
                        .anyRequest().permitAll()
        ).formLogin(
                form -> {
                    form.loginPage("/users/userLoginForm") // 우리가 만든 로그인폼으로 인터셉트됩니다.
                            .loginProcessingUrl("/userLogin")
                            .defaultSuccessUrl("/", true); // 로그인에 성공하면 돌아올 페이지
                }
        );
        return http.build();
    }
}
