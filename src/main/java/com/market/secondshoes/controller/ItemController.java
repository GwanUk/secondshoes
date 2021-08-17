package com.market.secondshoes.controller;

import com.market.secondshoes.argumentresolver.Login;
import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.UploadImage;
import com.market.secondshoes.dto.item.ItemAddDto;
import com.market.secondshoes.dto.item.ItemConditionDto;
import com.market.secondshoes.dto.item.ItemDetailDto;
import com.market.secondshoes.exception.ImageExceededException;
import com.market.secondshoes.exception.ImageExtException;
import com.market.secondshoes.service.ImageStore;
import com.market.secondshoes.service.ItemService;
import com.market.secondshoes.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class ItemController {

    private final ItemService itemService;
    private final ImageStore imageStore;
    private final MemberService memberService;

    @GetMapping("/sell/add")
    public String add(Model model) {

        model.addAttribute("itemAddDto", new ItemAddDto());

        return "itemAddForm";
    }

    @PostMapping("/sell/add")
    public String add(@Valid @ModelAttribute ItemAddDto itemAddDto, BindingResult bindingResult, @Login Long memberId) {

        List<UploadImage> uploadImages = null;

        try {
            uploadImages = imageStore.storeImages(itemAddDto.getImages());
        } catch (ImageExceededException e) {
            bindingResult.rejectValue("images", "imagesExceededCheck", e.getMessage());
        } catch (ImageExtException e) {
            bindingResult.rejectValue("images", "imagesExtCheck", e.getMessage());
        }

        if (bindingResult.hasErrors()) {
            return "itemAddForm";
        }

        Item item = Item.createItem();
        item.change(memberService.findMemberById(memberId), itemAddDto, uploadImages);
        itemService.itemAdd(item);

        return "redirect:/item/sell/add";
    }

    @PostMapping("/items")
    @ResponseBody
    public Page<ItemDetailDto> items(@RequestBody ItemConditionDto itemConditionDto) {
        Page<Item> page = itemService.search(itemConditionDto, PageRequest.of(itemConditionDto.getPage(), itemConditionDto.getSize()));
        return page.map(ItemDetailDto::createItemDetailDto);
    }

    @GetMapping("/image/{storeImageName}")
    @ResponseBody
    public Resource image(@PathVariable String storeImageName) throws MalformedURLException {
        return new UrlResource("file:" + imageStore.getFullPath(storeImageName));
    }
}
