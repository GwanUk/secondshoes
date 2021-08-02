package com.market.secondshoes.controller;

import com.market.secondshoes.Const;
import com.market.secondshoes.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @GetMapping("/")
    public String homeLogin(HttpServletRequest request, Model model) {

        Member loginMember = null;

        HttpSession session = request.getSession(false);

        if (session != null) {
            loginMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
        }

        model.addAttribute("member", loginMember);

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
