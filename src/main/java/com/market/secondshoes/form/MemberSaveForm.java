package com.market.secondshoes.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;


@Getter
@Setter
public class MemberSaveForm {

    @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
