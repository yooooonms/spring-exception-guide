package com.example.exception.domain.member.dto;

import com.example.exception.domain.member.entity.Member;
import com.example.exception.domain.value.Email;
import com.example.exception.domain.value.Name;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberReponse {

    private Email email;
    private Name name;

    public MemberReponse(Member member) {
        this.email = member.getEmail();
        this.name = member.getName();
    }

}
