package com.market.secondshoes.domain.item;

import lombok.Getter;

@Getter
public enum Category {
    OXFORD("옥스포드"), DERBY("더비"), WING_TIP("윙팁");

    private final String categoryType;

    Category(String categoryType) {
        this.categoryType = categoryType;
    }
}
