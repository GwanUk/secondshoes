package com.market.secondshoes.repository;

import com.market.secondshoes.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
