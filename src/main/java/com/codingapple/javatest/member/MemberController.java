package com.codingapple.javatest.member;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;


    @GetMapping("/register")
    public String register() {
        return "resister.html";
    }

    @PostMapping("member")
    public String addMember(
        String username,
        String password,
        String displayName
    ) {
        Member member = new Member();
        member.setUsername(username);

        var hash = new BCryptPasswordEncoder().encode(password);
        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
        
        return "redirect:/list";
    }
    

}
