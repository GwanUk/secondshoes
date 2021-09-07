package com.market.secondshoes.dto.item;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.item.ItemComment;
import com.market.secondshoes.domain.member.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
public class ItemCommentAddDto {

    private String comment;
    private Long itemId;
    private Long memberId;

    public ItemComment makeItemComment() {
        return ItemComment.createItemComment(comment, Item.createItem(itemId), Member.createMember(memberId));
    }
}
