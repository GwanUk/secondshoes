package com.market.secondshoes.service;

import com.market.secondshoes.domain.member.Member;
import com.market.secondshoes.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<Member> login(String email, String password) {
        return memberRepository.findMemberByEmail(email)
                .filter(m -> m.getPassword().equals(password));
    }
}
