package com.market.secondshoes.domain;

import com.market.secondshoes.dto.item.UploadImg;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item {

    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

//    private String tags;

    private Long price;
    private String explain;

    @ElementCollection
    @CollectionTable(name = "ITEM_IMAGES", joinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<UploadImg> images;
}
