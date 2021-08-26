package com.market.secondshoes.repository;

import com.market.secondshoes.domain.item.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {

    @EntityGraph(attributePaths = {"member"})
    Item findItemById(Long id);


}
