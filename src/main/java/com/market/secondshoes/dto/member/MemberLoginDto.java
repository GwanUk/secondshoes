package com.market.secondshoes.dto.member;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MemberLoginDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    private boolean maintain;
}
