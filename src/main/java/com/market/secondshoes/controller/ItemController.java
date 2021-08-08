package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.dto.item.ItemAddDto;
import com.market.secondshoes.service.ImageStore;
import com.market.secondshoes.service.ItemService;
import com.market.secondshoes.service.MemberService;
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
@RequiredArgsConstructor
@RequestMapping("/item")
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ImageStore imageStore;
    private final MemberService memberService;

    @Value("${file.dir}")
    private String fileDir;

    @GetMapping("/sell/add")
    public String add(@Login Long memberId, Model model) {

        model.addAttribute("memberId", memberId);
        model.addAttribute("itemAddDto", new ItemAddDto());

        return "itemAddForm";
    }

    @PostMapping("/sell/add")
    public String add(@Valid @ModelAttribute ItemAddDto itemAddDto, BindingResult bindingResult, @Login Long memberId, Model model) {

        model.addAttribute("memberId", memberId);

        if (bindingResult.hasErrors()) {
            log.info("### error ###{}", bindingResult);
            return "itemAddForm";
        }

        Item item = Item.createItem(itemAddDto, imageStore.storeImages(itemAddDto.getImages()));

        item.setMember(memberService.findMemberById(memberId).orElseThrow(() -> new RuntimeException("존재하지 않는 memberId입니다.")));

        log.info("@@@ {} @@@", item);

        itemService.itemAdd(item);

        return "redirect:/item/sell/add";
    }
}
