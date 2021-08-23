package com.market.secondshoes.domain.item;

import com.market.secondshoes.domain.BaseTimeEntity;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.item.ItemAddDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String title;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Size size;

    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Integer price;

    private String explain;

    @ElementCollection
    @CollectionTable(name = "ITEM_IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<UploadImage> uploadImages;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static Item createItem() {
        return new Item();
    }

    public void change(Member member, ItemAddDto itemAddDto, List<UploadImage> uploadImages) {
        title = itemAddDto.getTitle();
        gender = itemAddDto.getGender();
        size = itemAddDto.getSize();
        brand = itemAddDto.getBrand();
        category = itemAddDto.getCategory();
        price = itemAddDto.getPrice();
        explain = itemAddDto.getExplain();
        this.uploadImages = uploadImages;
        addMember(member);
    }

    public void change(Item item) {
        title = item.getTitle();
        gender = item.getGender();
        size = item.getSize();
        brand = item.getBrand();
        category = item.getCategory();
        price = item.getPrice();
        explain = item.getExplain();
        if (!item.getUploadImages().isEmpty()) {
            uploadImages = item.getUploadImages();
        }
    }

    private void addMember(Member member) {
        this.member = member;
        member.getItem().add(this);
    }
}
