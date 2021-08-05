package com.market.secondshoes.repository;

import com.market.secondshoes.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
