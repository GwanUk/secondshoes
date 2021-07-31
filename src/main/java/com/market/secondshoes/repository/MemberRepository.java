package com.market.secondshoes.repository;

import com.market.secondshoes.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findMemberByEmail(String email);

    public Optional<Member> findMemberByEmailAndPassword(String email, String password);

    public Optional<Member> findMemberByName(String name);
}
