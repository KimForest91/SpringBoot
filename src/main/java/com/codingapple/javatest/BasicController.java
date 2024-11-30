package com.codingapple.javatest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Controller 를 붙이면 class - 함수를 불러오지 않아도 실행시켜줌
@Controller
public class BasicController {
    @GetMapping("/")
    // @ResponseBody // 문자 그대로 보내주세요 라는 뜻
    String Hello() {
        return "index.html";
    }
   
    @GetMapping("/about")
    @ResponseBody
    String About() {
        return "<h1>About</h1>";
    }

    @GetMapping("/date")
    @ResponseBody
    String ShowDate() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);
        String stringDate = now.toString();
        return "<h1>접속시간: " + formattedDate + "</h1><h1>"+ stringDate +"</h1>";
    }
}
