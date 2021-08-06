package com.market.secondshoes.domain.member;

import com.market.secondshoes.dto.member.MemberSignUpDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String email;
    private String name;
    private String password;
    private Integer grade;

    @Embedded
    private Address address;

    public static Member createMember(String email, String name, String password) {
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.password = password;

        return member;
    }

    public static Member createMember(MemberSignUpDto memberSignUpDto) {
        return Member.createMember(memberSignUpDto.getEmail(), memberSignUpDto.getName(), memberSignUpDto.getPassword());
    }
}
