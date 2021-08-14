package com.market.secondshoes;

import com.market.secondshoes.domain.item.Item;
import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.dto.item.ItemAddDto;
import com.market.secondshoes.repository.ItemRepository;
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
            Member member = Member.createMember("test" + i + "@naver.com", "testName" + i, "test!" + i);
            memberRepository.save(member);
        }
    }
}
