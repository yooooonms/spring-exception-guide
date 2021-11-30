package com.example.exception.domain.member.application;

import com.example.exception.domain.member.dto.SignupRequest;
import com.example.exception.domain.member.entity.Member;
import com.example.exception.domain.member.exception.EmailDuplicatedException;
import com.example.exception.domain.member.repository.MemberRepository;
import com.example.exception.domain.value.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member signup(SignupRequest dto) {
        validateEmail(dto.getEmail());
        Member member = dto.toEntity();
        return memberRepository.save(member);
    }

    private void validateEmail(Email email) {
        if (memberRepository.existsByEmail(email)) {
            throw new EmailDuplicatedException(email);
        }
    }

}
