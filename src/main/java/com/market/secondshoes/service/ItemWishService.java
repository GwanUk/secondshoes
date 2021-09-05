package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.ItemWish;
import com.market.secondshoes.dto.item.ItemThumbDto;
import com.market.secondshoes.repository.ItemWishRepository;
import lombok.RequiredArgsConstructor;
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

    public List<ItemWish> findWishByItemId(Long itemId) {
        return itemWishRepository.findWishByItemId(itemId);
    }

    public List<ItemWish> findWishByMemberId(Long memberId) {
        return itemWishRepository.findWishByMemberId(memberId);
    }

    public List<ItemWish> findWishFetchByMemberId(Long itemId) {
        return itemWishRepository.findWishFetchByMemberId(itemId);
    }

    public Optional<ItemWish> findWishByItemIdAndMemberId(Long itemId, Long memberId) {
        return itemWishRepository.findWishByItemIdAndMemberId(itemId, memberId);
    }

    @Transactional
    public void wishDelete(Long wishId) {
        itemWishRepository.deleteById(wishId);
    }
}
