package com.basic.myboard.controller;

import com.basic.myboard.config.auth.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/user/showuser")
    @ResponseBody
    public PrincipalDetails test(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (principalDetails == null) {
            return null;
        }
        return principalDetails;
    }
}
