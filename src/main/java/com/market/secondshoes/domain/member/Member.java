package com.market.secondshoes.domain.member;

import com.market.secondshoes.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "member")
    private List<Item> item = new ArrayList<>();

    public static Member createMember(String email, String name, String password) {
        Member member = new Member();
        member.email = email;
        member.name = name;
        member.password = password;

        return member;
    }
}
