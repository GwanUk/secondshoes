package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.dto.item.ItemThumbDto;
import com.market.secondshoes.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
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

    public Page<ItemThumbDto> findAllItems(Pageable pageable) {
        return itemRepository.findAll(pageable).map(ItemThumbDto::createItemThumb);

    }
}
