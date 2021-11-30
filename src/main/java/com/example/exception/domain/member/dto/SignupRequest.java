package com.example.exception.domain.member.dto;

import com.example.exception.domain.member.entity.Member;
import com.example.exception.domain.value.Email;
import com.example.exception.domain.value.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class SignupRequest {

    @Valid
    private Email email;

    @Valid
    private Name name;

    public SignupRequest(@Valid Email email, @Valid Name name) {
        this.email = email;
        this.name = name;
    }

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .build();
    }

}
