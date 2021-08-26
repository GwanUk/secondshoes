package com.market.secondshoes.domain;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Wish extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "WISH_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static Wish createWish(Long itemId, Long memberId) {
        Wish wish = new Wish();
        wish.item = Item.createItem(itemId);
        wish.member = Member.createMember(memberId);
        return wish;
    }
}
