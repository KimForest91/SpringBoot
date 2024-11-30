package com.codingapple.javatest;   // package 키워드로 패키지명을 지정해야 다른데서 해당 클래스를 사용할 수 있음

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.ui.Model;


@Controller
public class ItemController {
    @GetMapping("/list")
    // @ResponseBody // 문자 그대로 보내주세요 라는 뜻
    String list(Model model) {
        model.addAttribute("foodName", "로제엽떡");
        return "list.html";
    }
   

}
