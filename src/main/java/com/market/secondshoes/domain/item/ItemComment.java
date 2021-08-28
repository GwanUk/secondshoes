package com.market.secondshoes.domain.item;

import com.market.secondshoes.domain.BaseTimeEntity;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.item.ItemCommentAddDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemComment extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_COMMENT_ID")
    private Long id;

    @Lob
    @Column(length = 1023)
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static ItemComment createItemComment(ItemCommentAddDto itemCommentAddDto) {
        ItemComment itemComment = new ItemComment();
        itemComment.item = Item.createItem(itemCommentAddDto.getItemId());
        itemComment.member = Member.createMember(itemCommentAddDto.getMemberId());
        itemComment.comment= itemCommentAddDto.getComment();
        return itemComment;
    }
}
