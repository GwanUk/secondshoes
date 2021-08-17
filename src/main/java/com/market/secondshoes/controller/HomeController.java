package com.market.secondshoes.controller;

import com.market.secondshoes.dto.item.ItemConditionDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(Model model) {
        model.addAttribute("itemConditionDto", new ItemConditionDto());
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
