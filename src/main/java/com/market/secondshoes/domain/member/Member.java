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

    public static Member createMember() {
        return new Member();
    }

    /*login*/
    public void change(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    /*update*/
    public void change(String email, String name, String password, Address address) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.address = address;
    }

    /*update*/
    public void change(Member member) {
        email = member.getEmail();
        name = member.getName();
        password = member.getPassword();
        address = member.getAddress();
    }

}
