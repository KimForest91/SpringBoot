package com.codingapple.javatest;   // package 키워드로 패키지명을 지정해야 다른데서 해당 클래스를 사용할 수 있음

import java.util.ArrayList;
import java.util.List;

import org.springframework.ui.Model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    // @ResponseBody // 문자 그대로 보내주세요 라는 뜻

    String list(Model model) {
        List<Item> result = itemRepository.findAll();

        var a = new Item();
        System.out.println("=======================" + a.toString());
        model.addAttribute("items", result);

        return "list.html";
    }
}
