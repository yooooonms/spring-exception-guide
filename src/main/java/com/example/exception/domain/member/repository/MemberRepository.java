package com.example.exception.domain.member.repository;

import com.example.exception.domain.member.entity.Member;
import com.example.exception.domain.value.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(Email email);

    boolean existsByEmail(Email email);

}
