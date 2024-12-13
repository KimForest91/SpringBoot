package com.codingapple.javatest;   // package 키워드로 패키지명을 지정해야 다른데서 해당 클래스를 사용할 수 있음

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;

    @GetMapping("/list")
    // @ResponseBody // 문자 그대로 보내주세요 라는 뜻
    String list(Model model) {
        List<Item> result = itemRepository.findAll();

        var a = new Item();
        // System.out.println("=======================" + a.toString());
        model.addAttribute("items", result);
        
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {
        System.out.println("==================================");
        
        System.out.println("title: " + title + " price22222222222222: " + price);
        System.out.println("title: " + title + " price: " + price);
        Item item = new Item();
        item.setTitle(title);
        item.setPrice(price);
        itemRepository.save(item);
        return "redirect:/list";
    }
    
    @GetMapping("/detail/{id}")
    String getDetail(@PathVariable Integer id, Model model) {
        Item item = itemRepository.findById((long) id)
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        model.addAttribute("item", item);
        return "detail.html";

        /* 
        Optional<Item> item = itemRepository.findById((long) id);

        if(item.isPresent()) {
            model.addAttribute("item", item.get());
            return "detail.html";
        } else {
            return "error.html";
        } */
        
    }


    /*
    String getDetail(@PathVariable Integer id, Model model) {
        Optional<Item> item = itemRepository.findById((long) id)
            .ifPresent(item -> model.addAttribute("item", item.get()));

        if(item.isPresent()) {
            model.addAttribute("item", item.get());
        }

        return "detail.html";
    }
    */

}
