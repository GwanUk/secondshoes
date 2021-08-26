package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.Wish;
import com.market.secondshoes.service.WishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wish")
public class WishController {

    private final WishService wishService;

    @GetMapping("/save/{itemId}")
    @ResponseBody
    public String wishSave(@PathVariable Long itemId, @Login Long memberId) {
        wishService.wishSave(itemId, memberId);
        return "success";
    }

    @GetMapping("/find/item/{itemId}")
    @ResponseBody
    public Integer findWishByItemId(@PathVariable Long itemId) {
        return wishService.findWishByItemId(itemId).size();
    }

    @GetMapping("/find/member/{itemId}")
    @ResponseBody
    public List<Wish> findWishByMemberId(@Login Long memberId) {
        return wishService.findWishByMemberId(memberId);
    }
}
