package com.market.secondshoes.dto.member;

import com.market.secondshoes.domain.member.Address;
import com.market.secondshoes.domain.member.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberInfoDto {

    private Long id;
    private String email;
    private String name;
    private Integer grade;
    private Address address;

    public static MemberInfoDto createMemberInfoDto(Member member) {
        MemberInfoDto memberInfoDto = new MemberInfoDto();
        memberInfoDto.id = member.getId();
        memberInfoDto.email = member.getEmail();
        memberInfoDto.name = member.getName();
        memberInfoDto.grade = member.getGrade();
        memberInfoDto.address = member.getAddress();
        return memberInfoDto;
    }
}
