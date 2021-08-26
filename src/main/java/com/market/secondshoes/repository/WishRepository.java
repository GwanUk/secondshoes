package com.market.secondshoes.repository;

import com.market.secondshoes.domain.Wish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishRepository extends JpaRepository<Wish, Long> {

    List<Wish> findWishByItemId(Long itemId);

    List<Wish> findWishByMemberId(Long memberId);

    Optional<Wish> findWishByItemIdAndMemberId(Long itemId, Long memberId);


}
