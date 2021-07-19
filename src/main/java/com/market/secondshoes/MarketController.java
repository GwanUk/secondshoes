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

    @GetMapping("/search")
    static String search() {
        return "search";
    }


}
