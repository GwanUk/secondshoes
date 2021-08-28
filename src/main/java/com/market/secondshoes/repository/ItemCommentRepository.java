package com.market.secondshoes.repository;

import com.market.secondshoes.domain.item.ItemComment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemCommentRepository extends JpaRepository<ItemComment, Long> {

    @EntityGraph(attributePaths = {"member"})
    List<ItemComment> findItemCommentByItemId(Long itemId);

}
