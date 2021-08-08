package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item itemAdd(Item item) {
        return itemRepository.save(item);
    }
}
