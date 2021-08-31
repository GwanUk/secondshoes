package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.dto.item.*;
import com.market.secondshoes.exception.ImageExceededException;
import com.market.secondshoes.exception.ImageExtException;
import com.market.secondshoes.service.ImageStore;
import com.market.secondshoes.service.ItemService;
import com.market.secondshoes.service.MemberService;
import com.market.secondshoes.service.ItemWishService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item")
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ImageStore imageStore;
    private final MemberService memberService;
    private final ItemWishService itemWishService;

    @GetMapping("/addForm")
    public String itemAddForm(Model model) {
        model.addAttribute("itemAddDto", new ItemAddDto());
        return "item/itemAddForm";
    }

    @PostMapping("/save")
    public String itemSave(@Valid @ModelAttribute ItemAddDto itemAddDto, BindingResult bindingResult, @Login Long memberId, @RequestParam(required = false) Long id) {
        List<UploadImage> uploadImages = null;

        try {
            uploadImages = imageStore.storeImages(itemAddDto.getImages());
        } catch (ImageExceededException e) {
            bindingResult.rejectValue("images", "imagesExceededCheck", e.getMessage());
        } catch (ImageExtException e) {
            bindingResult.rejectValue("images", "imagesExtCheck", e.getMessage());
        }

        if (bindingResult.hasErrors()) {
            return "item/itemAddForm";
        }

        Item item = Item.createItem();
        item.change(memberService.findMemberById(memberId), itemAddDto, uploadImages);

        if (id != null) {
            itemService.itemUpdate(id, item);
            return "redirect:/item/find/" + id;
        }

        itemService.itemSave(item);

        return "redirect:/item/find/" + item.getId();
    }

    @PostMapping("/findAll/{size}/{number}")
    @ResponseBody
    public Page<ItemThumbDto> findAllItem(@RequestBody(required = false) ItemConditionDto itemConditionDto, @PathVariable Integer size, @PathVariable Integer number, @Login Long loginId) {
        Page<Item> page = itemService.findAll(itemConditionDto, PageRequest.of(number, size));
        return page.map(item -> {
            ItemThumbDto itemThumbDto = ItemThumbDto.createItemThumbDto(item);
            if (loginId != null && itemWishService.findWishByItemIdAndMemberId(item.getId(), loginId).isPresent()) {
                itemThumbDto.setWished(true);
            }
            return itemThumbDto;
        });
    }

    @GetMapping("/findSellItems")
    public String findSellItems(Pageable pageable , @Login Long loginId, Model model) {
        log.info("@@@@ [{}] [{}]",pageable,loginId);
        Page<ItemSellListDto> page = itemService.findSellItems(loginId, pageable).map(ItemSellListDto::createItemSellListDto);
        model.addAttribute("itemSellListDtoPage", page);
        return "/item/itemSellList";
    }

    @GetMapping("/find/{id}")
    public String itemFindOne(@PathVariable Long id, Model model) {
        model.addAttribute("itemDetailDto", ItemDetailDto.createItemDetailDto(itemService.findItemById(id)));
        model.addAttribute("itemCommentAddDto", ItemCommentAddDto.createItemCommentAddDto(id));
        return "item/itemDetailForm";
    }

    @GetMapping("/image/{storeImageName}")
    @ResponseBody
    public Resource image(@PathVariable String storeImageName) throws MalformedURLException {
        return new UrlResource("file:" + imageStore.getFullPath(storeImageName));
    }

    @GetMapping("/updateForm/{id}")
    public String itemUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("itemAddDto", ItemAddDto.createItemAddDto(itemService.findItemById(id)));
        return "item/itemAddForm";
    }

    @GetMapping("/remove/{id}")
    public String itemRemove(@PathVariable Long id) {
        itemService.itemDelete(id);
        return "redirect:/";
    }

    @GetMapping("/sellList")
    public String sellList(@Login Long loginId) {
        return "/item/itemSellList";
    }
}
