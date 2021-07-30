package com.market.secondshoes.controller;

import com.market.secondshoes.form.MemberSaveForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Slf4j
@Controller
public class MarketController {

    @GetMapping("/")
    static String index() {
        return "index";
    }

    @GetMapping("/item")
    static String itemForm() {
        return "itemForm";
    }

    @GetMapping("/edit")
    static String editForm() {
        return "editForm";
    }

    @GetMapping("/sell")
    static String sellForm() {
        return "sellForm";
    }

    @GetMapping("/cart")
    static String cartForm() {
        return "cartForm";
    }

    @GetMapping("/signUp")
    static String signUpForm(Model model) {
        model.addAttribute("memberSaveForm", new MemberSaveForm());
        return "signUpForm";
    }

    @PostMapping("/signUp")
    static String signUp(@Valid @ModelAttribute MemberSaveForm form, BindingResult bindingResult) {
        log.info("::{}  {}  {}::", form.getEmail(), form.getName(), form.getPassword());
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "signUpForm";
        }
        return "redirect:/";
    }

    @GetMapping("/login")
    static String loginForm() {
        return "loginForm";
    }

    @GetMapping("/chat")
    static String chatForm() {
        return "chatForm";
    }

    @GetMapping("/sellList")
    static String sellList() {
        return "sellList";
    }

    @GetMapping("/buyList")
    static String buyList() {
        return "buyList";
    }

    @GetMapping("/wishList")
    static String wishList() {
        return "wishList";
    }

}
