package com.market.secondshoes.service;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findMemberById(Long id) {
        return memberRepository.findMemberById(id);
    }

    @Transactional
    public Long join(Member member) {
        validateDuplicateEmail(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateEmail(Member member) {
        Optional<Member> findMemberByEmail = memberRepository.findMemberByEmail(member.getEmail());
        if (findMemberByEmail.isPresent()) {
            throw new IllegalStateException("이미 가입된 이메일입니다.");
        }
    }

    public Optional<Member> login(String email, String password) {
        return memberRepository.findMemberByEmail(email)
                .filter(m -> m.getPassword().equals(password));
    }

}
