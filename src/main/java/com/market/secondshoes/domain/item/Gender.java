package com.market.secondshoes.domain.item;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

@Getter
public enum Gender {
    MALE("남성"), FEMALE("여성");

    private final String sex;

    @JsonCreator
    Gender(String sex) {
        this.sex = sex;
    }
}
