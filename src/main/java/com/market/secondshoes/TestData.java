package com.market.secondshoes;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class TestData {

    private final MemberRepository memberRepository;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 2; i++) {
            memberRepository.save(Member.createMember("test" + i + "@naver.com", "testName" + i, "test!" + i));
        }

    }
}
