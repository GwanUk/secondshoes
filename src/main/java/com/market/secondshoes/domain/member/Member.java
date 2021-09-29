package com.market.secondshoes.domain.member;

import com.market.secondshoes.domain.BaseTimeEntity;
import com.market.secondshoes.domain.item.ItemComment;
import com.market.secondshoes.domain.item.ItemWish;
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
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    private String email;
    private String name;
    private String password;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "member")
    private List<Item> item = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<ItemWish> itemWishes;

    @OneToMany(mappedBy = "member")
    private List<ItemComment> comments;

    public static Member createMember() {
        return new Member();
    }

    public static Member createMember(Long id) {
        Member member = new Member();
        member.id = id;
        return member;
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
