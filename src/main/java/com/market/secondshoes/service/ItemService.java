package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
}
