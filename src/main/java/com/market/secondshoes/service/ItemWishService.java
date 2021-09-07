package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.ItemWish;
import com.market.secondshoes.dto.item.ItemThumbDto;
import com.market.secondshoes.repository.ItemWishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemWishService {

    private final ItemWishRepository itemWishRepository;
    private final ItemService itemService;

    @Transactional
    public ItemWish wishSave(Long itemId, Long memberId) {
        itemService.wishCountPlus(itemId);
        return itemWishRepository.save(ItemWish.createWish(itemId, memberId));
    }

    public Slice<ItemWish> findWishFetchByMemberId(Long itemId, Pageable pageable) {
        return itemWishRepository.findWishFetchByMemberId(itemId, pageable);
    }

    public Optional<ItemWish> findWishByItemIdAndMemberId(Long itemId, Long memberId) {
        return itemWishRepository.findWishByItemIdAndMemberId(itemId, memberId);
    }

    @Transactional
    public void wishDelete(Long wishId) {
        itemWishRepository.deleteById(wishId);
    }

    @Transactional
    public void remove(Long itemId, Long memberId) {
        itemWishRepository.deleteByItemIdAndMemberId(itemId, memberId);
    }
}
