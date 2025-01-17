package com.codingapple.javatest.Comment;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.codingapple.javatest.member.CustomUser;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class CommentController {
    private final CommentRepository commentRepository;

    @PostMapping("/comment")
    @PreAuthorize("isAuthenticated()")
    String postComment(
        @RequestParam String content,
        @RequestParam Long itemId, 
        Authentication auth
    ) {
        String username;
        
        if (auth == null) {
            System.out.println("auth가 null임=====");
            username = "김무명";
            return "redirect:/login";
        }

        try {
            CustomUser user = (CustomUser) auth.getPrincipal();
            
            username = user.getUsername();

            var data = new Comment();
            data.setContent(content);
            data.setUsername(user.getUsername());
            data.setItemId(itemId);
            commentRepository.save(data);
            return "redirect:/detail/" + itemId;
            
        } catch (Exception e) {
            System.out.println("댓글저장 실패=====");
            return "redirect:/login";
        }
    }
}
