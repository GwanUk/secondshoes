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
        return itemCommentService.findItemCommentByItemId(itemId).stream().map(ItemCommentDto::createItemCommentDto).collect(Collectors.toList());
    }

    @GetMapping("/ajax/edit/{id}/{itemId}")
    public String commentEdit(@PathVariable Long id, @PathVariable Long itemId, RedirectAttributes redirectAttributes) {
        itemCommentService.remove(id);
        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/comment/{itemId}";
    }

    @GetMapping("/ajax/remove/{id}/{itemId}")
    public String commentRemove(@PathVariable Long id, @PathVariable Long itemId, RedirectAttributes redirectAttributes) {
        itemCommentService.remove(id);
        redirectAttributes.addAttribute("itemId", itemId);
        return "redirect:/comment/{itemId}";
    }

    @PostMapping("/ajax/save")
    @ResponseBody
    public List<ItemCommentDto> commentSave(@RequestBody ItemCommentAddDto itemCommentAddDto) {
        itemCommentService.save(ItemComment.createItemComment(itemCommentAddDto));
        return commentFind(itemCommentAddDto.getItemId());
    }
}

