package com.market.secondshoes.domain.member;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Embeddable
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
