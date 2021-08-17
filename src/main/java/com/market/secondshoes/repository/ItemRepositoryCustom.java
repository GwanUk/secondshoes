package com.market.secondshoes.repository;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.dto.item.ItemConditionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> search(ItemConditionDto itemConditionDto, Pageable pageable);
}
