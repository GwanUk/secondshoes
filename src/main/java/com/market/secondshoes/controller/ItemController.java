package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.dto.item.ItemAddDto;
import com.market.secondshoes.dto.item.ItemFindDto;
import com.market.secondshoes.dto.item.ItemThumbDto;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.exception.ImageExceededException;
import com.market.secondshoes.exception.ImageExtException;
import com.market.secondshoes.service.ImageStore;
import com.market.secondshoes.service.ItemService;
import com.market.secondshoes.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public String add(Model model) {

        model.addAttribute("itemAddDto", new ItemAddDto());

        return "itemAddForm";
    }

    @PostMapping("/sell/add")
    public String add(@Valid @ModelAttribute ItemAddDto itemAddDto, BindingResult bindingResult, @Login Long memberId, Model model) {

        List<UploadImage> uploadImages = null;

        try {
            uploadImages = imageStore.storeImages(itemAddDto.getImages());
        } catch (ImageExceededException e) {
            bindingResult.rejectValue("images", "imagesExceededCheck", e.getMessage());
        } catch (ImageExtException e) {
            bindingResult.rejectValue("images", "imagesExtCheck", e.getMessage());
        }

        if (bindingResult.hasErrors()) {
            log.info("!!!!!! errors !!!!!! {}", bindingResult);
            return "itemAddForm";
        }

        Item item = Item.createItem();
        item.change(memberService.findMemberById(memberId), itemAddDto, uploadImages);
        itemService.itemAdd(item);

        return "redirect:/item/sell/add";
    }

    @GetMapping("/items")
    @ResponseBody
    public Page<ItemThumbDto> findAllItems(Pageable pageable) {
        return itemService.findAllItems(pageable);
    }

    @PostMapping("/items")
    @ResponseBody
    public ItemThumbDto items_option(@RequestBody ItemFindDto itemFindDto) {
        log.info("### {}", itemFindDto);
        return null;
    }
}
