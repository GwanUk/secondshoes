package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.dto.item.ItemConditionDto;

import com.market.secondshoes.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public Item itemAdd(Item item) {
        return itemRepository.save(item);
    }

    public Page<Item> search(ItemConditionDto itemConditionDto, Pageable pageable) {
        return itemRepository.search(itemConditionDto, pageable);
    }

    public Item findItemById(Long id) {
        return itemRepository.findItemById(id);
    }

}
