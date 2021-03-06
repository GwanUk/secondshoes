package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.dto.item.ItemConditionDto;
import com.market.secondshoes.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item itemSave(Item item) {
        return itemRepository.save(item);
    }

    @Transactional
    public void itemUpdate(Long id, Item item) {
        itemRepository.findItemById(id).change(item);
    }

    @Transactional
    public void itemDelete(Long id) {
        itemRepository.deleteById(id);
    }

    @Transactional
    public void viewCountPlus(Item item) {
        item.viewCountPlus();
    }

    @Transactional
    public void wishCountPlus(Long id) {
        itemRepository.findItemById(id).wishCountPlus();
    }

    public Page<Item> findAll(ItemConditionDto itemConditionDto, Pageable pageable) {
        return itemRepository.findAll(itemConditionDto, pageable);
    }

    public Slice<Item> findSellItems(Long id, Pageable pageable) {
        return itemRepository.findItemsByMemberId(id, pageable);
    }

    public Item findItemById(Long id) {
        return itemRepository.findItemById(id);
    }


}
