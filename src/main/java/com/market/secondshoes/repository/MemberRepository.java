package com.market.secondshoes.repository;

import com.market.secondshoes.domain.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findMemberByEmail(String email);

    Optional<Member> findMemberByEmailAndPassword(String email, String password);

    Member findMemberById(Long id);
}
