package com.market.secondshoes.service;

import com.market.secondshoes.domain.Wish;
import com.market.secondshoes.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class WishService {

    private final WishRepository wishRepository;

    @Transactional
    public Wish wishSave(Long itemId, Long memberId) {
        return wishRepository.save(Wish.createWish(itemId, memberId));
    }

    public List<Wish> findWishByItemId(Long itemId) {
        return wishRepository.findWishByItemId(itemId);
    }

    public List<Wish> findWishByMemberId(Long memberId) {
        return wishRepository.findWishByMemberId(memberId);
    }

    public Optional<Wish> findWishByItemIdAndMemberId(Long itemId, Long memberId) {
        return wishRepository.findWishByItemIdAndMemberId(itemId, memberId);
    }
}
