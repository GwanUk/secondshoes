package com.market.secondshoes.domain.item;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.item.ItemAddDto;
import com.market.secondshoes.dto.item.UploadImage;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

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
    private List<UploadImage> images;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    public static Item createItem() {
        return new Item();
    }

    public void change(Member member, ItemAddDto itemAddDto, List<UploadImage> uploadImage) {
        title = itemAddDto.getTitle();
        gender = itemAddDto.getGender();
        size = itemAddDto.getSize();
        brand = itemAddDto.getBrand();
        category = itemAddDto.getCategory();
        price = itemAddDto.getPrice();
        explain = itemAddDto.getExplain();
        images = uploadImage;
        addMember(member);
    }

    private void addMember(Member member) {
        this.member = member;
        member.getItem().add(this);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", gender=" + gender +
                ", size=" + size +
                ", brand=" + brand +
                ", category=" + category +
                ", price=" + price +
                ", explain='" + explain + '\'' +
                ", images=" + images +
                ", member=" + member +
                '}';
    }
}
