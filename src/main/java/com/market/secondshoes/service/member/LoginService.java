package com.market.secondshoes.service.member;

import com.market.secondshoes.domain.Member;
import com.market.secondshoes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Optional<Member> login(String email, String password) {
        return memberRepository.findMemberByEmail(email)
                .filter(m -> m.getPassword().equals(password));
    }

}
