package com.basic.myboard.config.auth;

import com.basic.myboard.entity.User;
import com.basic.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// /login 자동 UserDetailsService 타입으로 IoC loadUserByUserName();
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null)
            System.out.println(" 유저 디테일 객체 생성 !!! " + userEntity);
        return new PrincipalDetails(userEntity);
    }
}
