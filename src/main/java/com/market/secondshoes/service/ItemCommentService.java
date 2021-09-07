package com.market.secondshoes.service;

import com.market.secondshoes.domain.item.ItemComment;
import com.market.secondshoes.repository.ItemCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemCommentService {

    private final ItemCommentRepository itemCommentRepository;

    public List<ItemComment> findItemCommentByItemId(Long itemId) {
        return itemCommentRepository.findItemCommentByItemId(itemId);
    }

    @Transactional
    public ItemComment itemCommentSave(ItemComment itemComment) {
        return itemCommentRepository.save(itemComment);
    }

    @Transactional
    public void remove(Long id) {
        itemCommentRepository.deleteById(id);
    }

    @Transactional
    public void save(ItemComment itemComment) {
        itemCommentRepository.save(itemComment);
    }

    @Transactional
    public void update(Long id, ItemComment itemComment) {
        itemCommentRepository.findById(id).get().itemCommentChange(itemComment);
    }
}
