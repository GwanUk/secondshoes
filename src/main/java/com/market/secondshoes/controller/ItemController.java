package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.dto.item.*;
import com.market.secondshoes.exception.ImageExceededException;
import com.market.secondshoes.exception.ImageExtException;
import com.market.secondshoes.service.ImageStore;
import com.market.secondshoes.service.ItemService;
import com.market.secondshoes.service.ItemWishService;
import com.market.secondshoes.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.*;
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

    @PostMapping("/findAll")
    @ResponseBody
    public Page<ItemThumbDto> findAllItem(@RequestBody(required = false) ItemConditionDto itemConditionDto, Pageable pageable, @Login Long loginId) {
        log.info("@@@[{}]",pageable.toString());
        Page<Item> page = itemService.findAll(itemConditionDto, pageable);
        return page.map(item -> {
            ItemThumbDto itemThumbDto = ItemThumbDto.createItemThumbDto(item);
            if (loginId != null && itemWishService.findWishByItemIdAndMemberId(item.getId(), loginId).isPresent()) {
                itemThumbDto.wished();
            }
            return itemThumbDto;
        });
    }

    @GetMapping("/findSellItems")
    public String findSellItems(Pageable pageable , @Login Long loginId, Model model) {
        Slice<ItemSellListDto> itemSellListDtoSlice = itemService.findSellItems(loginId, pageable).map(ItemSellListDto::createItemSellListDto);
        model.addAttribute("itemSellListDtoSlice", itemSellListDtoSlice);
        return "/item/itemSellList";
    }

    @GetMapping("/find/{id}")
    public String itemFindOne(@PathVariable Long id, Model model, @Login Long loginId) {
        Item item = itemService.findItemById(id);
        itemService.viewCountPlus(item);
        ItemDetailDto itemDetailDto = ItemDetailDto.createItemDetailDto(item);
        if (loginId != null && itemWishService.findWishByItemIdAndMemberId(id, loginId).isPresent()) {
            itemDetailDto.wished();
        }
        model.addAttribute("itemDetailDto", itemDetailDto);
        return "item/itemDetailForm";
    }

    @GetMapping("/image/{storeImageName}")
    @ResponseBody
    public Resource image(@PathVariable String storeImageName) throws MalformedURLException {
        return new UrlResource("file:" + imageStore.getFullPath(storeImageName));
    }

    @GetMapping("/updateForm/{id}")
    public String itemUpdate(@PathVariable Long id, Model model) {
        Item item = itemService.findItemById(id);
        model.addAttribute("itemAddDto", ItemAddDto.createItemAddDto(item));
        model.addAttribute("images", item.getUploadImages());
        return "item/itemAddForm";
    }

    @GetMapping("/remove/{id}")
    public String itemRemove(@PathVariable Long id) {
        itemService.itemDelete(id);
        return "redirect:/";
    }
}
