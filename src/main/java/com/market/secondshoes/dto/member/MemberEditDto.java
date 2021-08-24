package com.market.secondshoes.dto.member;

import com.market.secondshoes.domain.member.Address;
import com.market.secondshoes.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberEditDto {

    private Long id;
    private String email;
    private String name;
    private String password;
    private String passwordCheck;
    private Address address;



    public Member makeMember() {
        Member member = Member.createMember();
        member.change(email, name, password, address);
        return member;
    }

    public static MemberEditDto createMemberEditDto(Member member) {
        MemberEditDto memberEditDto = new MemberEditDto();
        memberEditDto.id = member.getId();
        memberEditDto.email = member.getEmail();
        memberEditDto.name = member.getName();
        memberEditDto.address = member.getAddress();
        return memberEditDto;
    }
}
