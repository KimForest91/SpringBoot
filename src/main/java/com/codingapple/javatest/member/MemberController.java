package com.codingapple.javatest.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.security.core.Authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;

    @GetMapping("/register")
    public String register(Authentication auth) {

        if (auth.isAuthenticated()){
            return "redirect:/list";
        }
        
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

    @GetMapping("/mypage")
    public String mypage(Authentication auth) {
        CustomUser result = (CustomUser) auth.getPrincipal();
        System.out.println(result.displayName);

        /*         
        System.out.println(auth);
        System.out.println(auth.getName());
        System.out.println(auth.isAuthenticated()); 
        */
        
        return "mypage.html";
    }

    @GetMapping("/user/1")
    @ResponseBody
    public Member getUser() {
        var a = memberRepository.findById(1L);
        var result = a.get();
        
        return result;
    }
    
    
}
