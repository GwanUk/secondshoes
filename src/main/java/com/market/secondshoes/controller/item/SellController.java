package com.market.secondshoes.controller.item;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.dto.item.ItemSellDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/sell")
@RequiredArgsConstructor
@Slf4j
public class SellController {

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/add")
    public String sellForm(@Login Long loginMemberId, Model model) {

        model.addAttribute("loginMemberId", loginMemberId);
        model.addAttribute("itemSellDto", new ItemSellDto());

        return "sellForm";
    }

    @PostMapping("/add")
    public String sell(@Valid @ModelAttribute ItemSellDto itemSellDto, BindingResult bindingResult, @Login Long loginMemberId, Model model) {

        model.addAttribute("loginMemberId", loginMemberId);

        if (bindingResult.hasErrors()) {
            return "sellForm";
        }



        return "redirect:/sell/add";
    }
}
