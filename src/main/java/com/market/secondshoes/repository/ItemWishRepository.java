package com.market.secondshoes.repository;

import com.market.secondshoes.domain.item.ItemWish;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemWishRepository extends JpaRepository<ItemWish, Long> {

    @EntityGraph(attributePaths = {"item"})
    Slice<ItemWish> findWishFetchByMemberId(Long memberId, Pageable pageable);

    Optional<ItemWish> findWishByItemIdAndMemberId(Long itemId, Long memberId);

    void deleteByItemIdAndMemberId(Long itemId, Long memberId);
}
