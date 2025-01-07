package com.codingapple.javatest.member;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public void saveMember(
        String username, 
        String password, 
        String displayName) throws Exception {
        var result = memberRepository.findByUsername(username);

        if(result.isPresent()) {
            throw new Exception("이미 존재하는 유저네임입니다.");
        }

        if(username.length() < 3) {
            throw new Exception("유저네임은 3글자 이상이어야 합니다.");
        }

        if(password.length() < 4) {
            throw new Exception("비밀번호는 4글자 이상이어야 합니다.");
        }

        Member member = new Member();
        member.setUsername(username);

        var hash = passwordEncoder.encode(password);

        member.setPassword(hash);
        member.setDisplayName(displayName);
        memberRepository.save(member);
    }
}
