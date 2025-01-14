package com.codingapple.javatest.Comment;

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
    String postComment(
        @RequestParam String content,
        @RequestParam Long item, 
        Authentication auth
    ) {
        CustomUser user = (CustomUser) auth.getPrincipal();
        var data = new Comment();
        data.setContent(content);
        data.setUsername(user.getName());
        data.setItemId(item);
        commentRepository.save(data);
        return "redirect:/list";
    }

}
