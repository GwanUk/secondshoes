package com.market.secondshoes.controller;

import com.market.secondshoes.domain.item.ItemComment;
import com.market.secondshoes.dto.item.ItemCommentAddDto;
import com.market.secondshoes.dto.item.ItemCommentDto;
import com.market.secondshoes.service.ItemCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
@Slf4j
public class ItemCommentController {

    private final ItemCommentService itemCommentService;

    @GetMapping("/{itemId}")
    @ResponseBody
    public List<ItemCommentDto> commentFind(@PathVariable Long itemId) {
        log.info("@@@@@ {}", itemId);
        return itemCommentService.findItemCommentByItemId(itemId).stream().map(ItemCommentDto::createItemCommentDto).collect(Collectors.toList());
    }

    @PostMapping
    public String commentSave(@ModelAttribute ItemCommentAddDto itemCommentAddDto, RedirectAttributes redirectAttributes) {
        log.info("@@@@@ {}", itemCommentAddDto);
        itemCommentService.itemCommentSave(ItemComment.createItemComment(itemCommentAddDto));
        redirectAttributes.addAttribute("itemId", itemCommentAddDto.getItemId());
        return "redirect:/item/find/{itemId}";
    }
}
