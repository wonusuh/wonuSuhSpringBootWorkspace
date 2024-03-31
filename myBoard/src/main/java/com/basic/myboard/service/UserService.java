package com.basic.myboard.service;

import com.basic.myboard.entity.User;
import com.basic.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void save(User u) {
        userRepository.save(u);
    }

    public List<User> getAllUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
