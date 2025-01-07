package com.codingapple.javatest.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;


    @GetMapping("/register")
    public String register() {
        return "register.html";
    }

    @PostMapping("/member")
    public String addMember(
        String username,
        String password,
        String displayName
    ) {
        try {
            memberService.saveMember(username, password, displayName);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "redirect:/list";
    }

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}
