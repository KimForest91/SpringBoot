package com.codingapple.javatest.Item;   // package 키워드로 패키지명을 지정해야 다른데서 해당 클래스를 사용할 수 있음

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;

    @GetMapping("/list")
    // @ResponseBody // 문자 그대로 보내주세요 라는 뜻
    String list(Model model) {
        List<Item> result = itemRepository.findAll();

        model.addAttribute("items", result);
        
        return "list.html";
    }

    @GetMapping("/write")
    String write() {
        return "write.html";
    }

    @PostMapping("/add")
    String addPost(String title, Integer price) {
        itemService.saveItem(title, price);

        return "redirect:/list";
    }


    @PostMapping("/edit")
    String editItemString(Long id, String title, Integer price) {
        itemService.updateItem(id, title, price);
        return "redirect:/list";
    }

    @GetMapping("/update/{id}")
    String updateItemString(@PathVariable Long id, Model model) {
        List<Item> result = itemRepository.findAll();
        model.addAttribute("id", id);
        model.addAttribute("items", result);

        return "update.html";
    }

    
    @GetMapping("/detail/{id}")
    String getDetail(@PathVariable Integer id, Model model) {
        Item item = itemRepository.findById((long) id)
            .orElseThrow(() -> new RuntimeException("상품을 찾을 수 없습니다."));

        model.addAttribute("item", item);
        return "detail.html";

    }
}
