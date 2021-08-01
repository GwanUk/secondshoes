package com.market.secondshoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/{id}")
    public String loginHome(@PathVariable String id) {
        System.out.println(" ! loginId = " + id);
        return "home";
    }

    @GetMapping("/item")
    public String itemForm() {
        return "itemForm";
    }

    @GetMapping("/edit")
    public String editForm() {
        return "editForm";
    }

    @GetMapping("/sell")
    public String sellForm() {
        return "sellForm";
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
