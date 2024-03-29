package com.basic.myboard.service;

import com.basic.myboard.entity.User;
import com.basic.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void save(User u) {
        userRepository.save(u);
    }
}
