package com.market.secondshoes.controller.item;

import com.market.secondshoes.ConstShoes;
import com.market.secondshoes.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {



    @GetMapping("/item")
    public String itemForm(@SessionAttribute(name = ConstShoes.LOGIN_MEMBER_ID, required = false) Long loginMemberId, Model model) {

        model.addAttribute("loginMemberId", loginMemberId);

        return "itemForm";
    }

    @GetMapping("/sell")
    public String sellForm(@SessionAttribute(name = ConstShoes.LOGIN_MEMBER_ID, required = false) Long loginMemberId, Model model) {

        model.addAttribute("loginMemberId", loginMemberId);

        return "sellForm";
    }
}
