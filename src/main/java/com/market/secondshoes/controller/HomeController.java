package com.market.secondshoes.controller;

import com.market.secondshoes.dto.item.ItemDetailDto;
import com.market.secondshoes.dto.item.ItemFindDto;
import com.market.secondshoes.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    ItemService itemService;

    @GetMapping("/")
    public String homeLogin(Model model) {
        model.addAttribute("itemFindDto", new ItemFindDto());
        return "index";
    }

    @GetMapping("/item")
    public String itemForm() {
        return "itemForm";
    }



    @GetMapping("/edit")
    public String editForm() {
        return "editForm";
    }



    @GetMapping("/cart")
    public String cartForm() {
        return "cartForm";
    }

    @GetMapping("/chat")
    public String chatForm() {
        return "chatForm";
    }

    @GetMapping("/sellList")
    public String sellList() {
        return "sellList";
    }

    @GetMapping("/buyList")
    public String buyList() {
        return "buyList";
    }

    @GetMapping("/wishList")
    public String wishList() {
        return "wishList";
    }
}
