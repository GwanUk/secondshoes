package com.market.secondshoes.domain.item;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("남성"), FEMALE("여성");

    private final String sex;

    Gender(String sex) {
        this.sex = sex;
    }
}
