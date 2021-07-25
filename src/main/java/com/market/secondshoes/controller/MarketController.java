package com.market.secondshoes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {

    @GetMapping("/")
    static String index() {
        return "index";
    }

    @GetMapping("/itemForm")
    static String itemForm() {
        return "itemForm";
    }

    @GetMapping("/editForm")
    static String editForm() {
        return "editForm";
    }

    @GetMapping("/sellForm")
    static String sellForm() {
        return "sellForm";
    }

    @GetMapping("/cartForm")
    static String cartForm() {
        return "cartForm";
    }

    @GetMapping("/signUpForm")
    static String signUpForm() {
        return "signUpForm";
    }

    @GetMapping("/loginForm")
    static String loginForm() {
        return "loginForm";
    }

    @GetMapping("/chatForm")
    static String chatForm() {
        return "chatForm";
    }

}
