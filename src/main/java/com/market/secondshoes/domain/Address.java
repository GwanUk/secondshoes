package com.market.secondshoes.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
@Getter
public class Address {

    @NotNull
    private Integer postcode;

    @NotNull
    private String address;

    @NotNull
    private String detailAddress;

    @NotNull
    private String extraAddress;
}
