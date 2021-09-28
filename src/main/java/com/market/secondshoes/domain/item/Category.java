package com.market.secondshoes.domain.item;

import lombok.Getter;
@Getter
public enum Category {
    SNEAKERS("스니커즈"), HIGH_TOP("하이탑"), SANDAL("샌들");

    private final String categoryType;

    Category(String categoryType) {
        this.categoryType = categoryType;
    }
}
