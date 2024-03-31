package com.basic.myboard.controller;

import com.basic.myboard.entity.User;
import com.basic.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/userJoinForm";
    }

    @PostMapping("/join")
    public String userJoin(User u) {
        log.trace("user id : " + u.getUserId());
        log.trace("user pw : " + u.getUserPw());
        log.trace("user name : " + u.getUserName());
        userService.save(u);
        return "redirect:/";
    }

    @GetMapping("/userList")
    public String userList(Model model) {
        List<User> userList = (ArrayList<User>) userService.getAllUser();
        model.addAttribute("userList", userList);
        return "/user/userList";
    }
}
