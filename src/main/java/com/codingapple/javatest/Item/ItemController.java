package com.codingapple.javatest.Item;   // package 키워드로 패키지명을 지정해야 다른데서 해당 클래스를 사용할 수 있음

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingapple.javatest.Comment.Comment;
import com.codingapple.javatest.Comment.CommentRepository;


@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemRepository itemRepository;
    private final ItemService itemService;
    private final CommentRepository commentRepository;

    /*     
    @GetMapping("/list")
    // @ResponseBody // 문자 그대로 보내주세요 라는 뜻
    String list(Model model) {
        List<Item> result = itemRepository.findAll();

        model.addAttribute("items", result);
        
        return "list.html";
    }  
    */

    @GetMapping("/list/page/{page}")
    String getListPage(Model model, @PathVariable Integer page) {
        Page<Item> result = itemRepository.findPageBy(PageRequest.of(page-1, 2));

        model.addAttribute("items", result.getContent());
        model.addAttribute("totalPages", result.getTotalPages());
        model.addAttribute("currentPage", page);
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
    String getDetail(@PathVariable Long id, Model model) {
        // 댓글 조회
        List<Comment> comment =  commentRepository.findAllByItemId(id);

        // 아이템 조회
        Optional<Item> result = itemRepository.findById(id);

        System.out.println("comment=============" + comment);

        if (result.isPresent()) {
            Item item = result.get();
            model.addAttribute("item", item);
            model.addAttribute("comment", comment);
            System.out.println("item=============" + item);
            return "detail.html";
        } else {
            return "redirect:/list";
        }

    }
}
