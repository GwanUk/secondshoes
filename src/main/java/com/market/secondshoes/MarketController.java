package com.market.secondshoes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {

    @GetMapping("/")
    static String index() {
        return "header";
    }

    @GetMapping("item")
    static String itemForm() {
        return "item";
    }

    @GetMapping("/login")
    static String loginForm() {
        return "login";
    }

    @GetMapping("/signup")
    static String signupForm() {
        return "signup";
    }

    @GetMapping("/cart")
    static String cartForm() {
        return "cart";
    }
}
