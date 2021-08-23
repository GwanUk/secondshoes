package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Commit
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    void itemAdd() {
        Item item = Item.createItem();
        Item savedItem = itemService.itemSave(item);
        assertEquals("호에엥", savedItem.getTitle());
    }
}