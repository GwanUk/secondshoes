package com.market.secondshoes.repository;

import com.market.secondshoes.domain.item.ItemWish;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemWishRepository extends JpaRepository<ItemWish, Long> {

    List<ItemWish> findWishByItemId(Long itemId);

    List<ItemWish> findWishByMemberId(Long memberId);

    @EntityGraph(attributePaths = {"item"})
    List<ItemWish> findWishFetchByMemberId(Long memberId);

    Optional<ItemWish> findWishByItemIdAndMemberId(Long itemId, Long memberId);


}
