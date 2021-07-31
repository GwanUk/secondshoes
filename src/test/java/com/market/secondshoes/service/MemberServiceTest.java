package com.market.secondshoes.service;

import com.market.secondshoes.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    public void join() {
        Member member = Member.createMember("abc@naver.com", "kim", "1234");

        Long memberId = memberService.join(member);
        System.out.println("member = " + member.getId());

        assertEquals(memberId, memberService.findMemberByEmail(member.getEmail()).get().getId());
    }

    @Test
    public void findAll() {
        Member member1 = Member.createMember("abc@naver.com", "kim", "1234");
        Member member2 = Member.createMember("abcd@naver.com", "key", "1234");

        memberService.join(member1);
        memberService.join(member2);

        assertEquals(2, memberService.findMemberAll().size());
    }

}