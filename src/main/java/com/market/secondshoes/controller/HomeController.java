package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.dto.item.ItemAddDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@Login Long memberId, Model model) {

        model.addAttribute("memberId", memberId);
        model.addAttribute("itemAddDto", new ItemAddDto());

        return "home";
    }

    @GetMapping("/item")
    public String itemForm(@Login Long memberId, Model model) {

        model.addAttribute("memberId", memberId);

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
