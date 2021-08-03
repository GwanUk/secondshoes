package com.market.secondshoes.controller;

import com.market.secondshoes.Const;
import com.market.secondshoes.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = Const.LOGIN_MEMBER, required = false) Member member, Model model) {

        model.addAttribute("member", member);

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
