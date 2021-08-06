package com.market.secondshoes.domain.item;

import lombok.Getter;

@Getter
public enum Brand {
    NIKE("나이키"), ADIDAS("아디다스"), PUMA("퓨마");

    private final String brandName;

    Brand(String brandName) {
        this.brandName = brandName;
    }
}
