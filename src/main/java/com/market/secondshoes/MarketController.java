package com.market.secondshoes;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MarketController {

    @GetMapping("/")
    static String face() {
        return "index";
    }

    @GetMapping("/login")
    static String login() {
        return "login";
    }

    @GetMapping("/signup")
    static String signup() {
        return "signup";
    }
}
