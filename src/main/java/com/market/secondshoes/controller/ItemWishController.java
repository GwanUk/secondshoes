package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.ItemWish;
import com.market.secondshoes.dto.item.ItemDetailDto;
import com.market.secondshoes.service.ItemWishService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/wish")
public class ItemWishController {

    private final ItemWishService itemWishService;

    @GetMapping("/form")
    public String wishForm(Pageable pageable, @Login Long loginId, Model model) {

        model.addAttribute("itemDetailDtoSlice", itemWishService.findWishFetchByMemberId(loginId, pageable).map(itemWish -> ItemDetailDto.createItemDetailDto(itemWish.getItem())));
        return "/item/itemWishList";
    }

    @GetMapping("/ajax/{itemId}")
    @ResponseBody
    public String wishSave(@PathVariable Long itemId, @Login Long loginId) {
        Optional<ItemWish> wish = itemWishService.findWishByItemIdAndMemberId(itemId, loginId);
        if (wish.isPresent()) {
            itemWishService.wishDelete(wish.get().getId());
            return "delete";
        }
        itemWishService.wishSave(itemId, loginId);
        return "save";
    }

    @GetMapping("/remove/{itemId}")
    public String wishRemove(@PathVariable Long itemId, @Login Long memberId) {
        itemWishService.remove(itemId, memberId);
        return "redirect:/wish/form?page=0&size=4";
    }
}
