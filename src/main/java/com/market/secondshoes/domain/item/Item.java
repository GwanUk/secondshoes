package com.market.secondshoes.domain.item;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.item.ItemAddDto;
import com.market.secondshoes.dto.item.UploadImage;

import javax.persistence.*;
import java.util.List;

@Entity
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

    private Long price;

    private String explain;

    @ElementCollection
    @CollectionTable(name = "ITEM_IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<UploadImage> images;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    protected Item() {
    }

    public static Item createItem(ItemAddDto itemAddDto, List<UploadImage> uploadImage) {
        Item item = new Item();
        item.title = itemAddDto.getTitle();
        item.gender = itemAddDto.getGender();
        item.size = itemAddDto.getSize();
        item.brand = itemAddDto.getBrand();
        item.category = itemAddDto.getCategory();
        item.price = itemAddDto.getPrice();
        item.explain = itemAddDto.getExplain();
        item.images = uploadImage;
        return item;
    }

    public void setMember(Member member) {
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
