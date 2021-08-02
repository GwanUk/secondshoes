package com.market.secondshoes.service;

import com.market.secondshoes.domain.Member;
import com.market.secondshoes.service.member.SignUpService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SignUpServiceTest {

    @Autowired
    SignUpService signUpService;

    @Test
    public void joinAndFind() {

        Member member1 = Member.createMember("abc@naver.com", "kim", "1234");
        Member member2 = Member.createMember("abcd@naver.com", "key", "1234");

        Long Id1 = signUpService.join(member1);
        Long Id2 = signUpService.join(member2);

        assertEquals(Id1, signUpService.findMemberByEmail(member1.getEmail()).get().getId());
        assertEquals(Id2, signUpService.findMemberByEmail(member2.getEmail()).get().getId());

        assertEquals(2, signUpService.findMemberAll().size());
    }

    @Test
    public void duplicateJoin() {
        Member member1 = Member.createMember("abc@naver.com", "kim", "1234");
        Member member2 = Member.createMember("abc@naver.com", "key", "1367");

        signUpService.join(member1);
        assertThrows(IllegalStateException.class, () -> signUpService.join(member2));

    }
}