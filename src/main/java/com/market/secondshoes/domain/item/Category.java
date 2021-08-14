package com.market.secondshoes.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
@Getter
public enum Category {
    OXFORD("옥스포드"), DERBY("더비"), WING_TIP("윙팁");

    private final String categoryType;

    @JsonCreator
    Category(String categoryType) {
        this.categoryType = categoryType;
    }
}
