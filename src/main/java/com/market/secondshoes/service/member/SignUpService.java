package com.market.secondshoes.service.member;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SignUpService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateEmail(member);
        memberRepository.save(member);
        return member.getId();
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findMemberByEmail(email);
    }

    public List<Member> findMemberAll() {
        return memberRepository.findAll();
    }

    private void validateDuplicateEmail(Member member) {
        Optional<Member> findMemberByEmail = memberRepository.findMemberByEmail(member.getEmail());
        if (findMemberByEmail.isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }
}
