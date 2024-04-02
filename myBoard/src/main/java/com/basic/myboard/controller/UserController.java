package com.basic.myboard.controller;

import com.basic.myboard.entity.User;
import com.basic.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/users/userJoinForm";
    }

    @PostMapping("/join")
    public String userJoin(User u) {
        String initPassword = u.getPassword();
        String enPassword = bCryptPasswordEncoder.encode(initPassword);
        u.setPassword(enPassword);
        userService.save(u);
        return "redirect:/";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("userList", userList);
        return "/users/userList";
    }

    @GetMapping("/loginForm")
    public String userLoginForm() {
        System.out.println("test");
        return "/users/userLoginForm";
    }

    @GetMapping("/user")
    @ResponseBody
    public String userTest() {
        return "user test";
    }
}
