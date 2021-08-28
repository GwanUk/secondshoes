package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.ItemWish;
import com.market.secondshoes.dto.item.ItemThumbDto;
import com.market.secondshoes.service.ItemWishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wish")
@Slf4j
public class ItemWishController {

    private final ItemWishService itemWishService;

    @GetMapping("/form")
    public String wishForm(@Login Long loginId, Model model) {
        model.addAttribute("itemThumbDtoList", itemWishService.findWishFetchByMemberId(loginId).stream().map(wish -> ItemThumbDto.createItemThumbDto(wish.getItem())).collect(Collectors.toList()));
        return "wishForm";
    }

    @GetMapping("/ajax/{itemId}")
    @ResponseBody
    public String wishSave(@PathVariable Long itemId, @Login Long loginId) {
        Optional<ItemWish> wish = itemWishService.findWishByItemIdAndMemberId(itemId, loginId);
        if (wish.isPresent()) {
            log.info("@@@@@@ {}", wish.get().getId());
            itemWishService.wishDelete(wish.get().getId());
            return "delete";
        }
        itemWishService.wishSave(itemId, loginId);
        return "save";
    }

    @GetMapping("/find/item/{itemId}")
    @ResponseBody
    public Integer findWishByItemId(@PathVariable Long itemId) {
        return itemWishService.findWishByItemId(itemId).size();
    }

    @GetMapping("/find/member/{itemId}")
    @ResponseBody
    public List<ItemWish> findWishByMemberId(@Login Long memberId) {
        return itemWishService.findWishByMemberId(memberId);
    }
}
