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
}
