package com.market.secondshoes.domain.item;

import com.market.secondshoes.dto.item.UploadImg;

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

    private Integer size;


    @Enumerated(EnumType.STRING)
    private Brand brand;

    @Enumerated(EnumType.STRING)
    private Category category;

    private Long price;

    private String explain;

    @ElementCollection
    @CollectionTable(name = "TAGS", joinColumns = @JoinColumn(name = "ITEM_ID"))
    @Column(name = "TAG")
    private List<String> tags;

    @ElementCollection
    @CollectionTable(name = "ITEM_IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<UploadImg> images;
}
