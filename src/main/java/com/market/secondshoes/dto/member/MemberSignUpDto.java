package com.market.secondshoes.dto.member;

import com.market.secondshoes.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


@Getter
@Setter
public class MemberSignUpDto {

    @NotEmpty @Email
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String password;

    @NotBlank
    private String passwordCheck;

    public Member makeMember() {
        return Member.createMember(this.getEmail(), this.getName(), this.getPassword());
    }
}
