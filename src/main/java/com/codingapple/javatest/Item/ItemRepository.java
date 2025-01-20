package com.codingapple.javatest.Item;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Page<Item> findPageBy(PageRequest page);
    Page<Item> findByTitleContaining(String title, PageRequest page);
}
