package com.market.secondshoes.domain.item;

import com.market.secondshoes.domain.BaseTimeEntity;
import com.market.secondshoes.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemWish extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "WISH_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static ItemWish createWish(Long itemId, Long memberId) {
        ItemWish itemWish = new ItemWish();
        itemWish.item = Item.createItem(itemId);
        itemWish.member = Member.createMember(memberId);
        return itemWish;
    }
}
