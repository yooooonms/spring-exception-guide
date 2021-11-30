package com.example.exception.domain.member.entity;

import com.example.exception.domain.value.Email;
import com.example.exception.domain.value.Name;
import com.example.exception.global.error.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"name", "email"})
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Embedded
    private Name name;

    @Embedded
    private Email email;

    @Builder
    public Member(Name name, Email email) {
        this.name = name;
        this.email = email;
    }

    public void updateProfile(final Name name) {
        this.name = name;
    }

}
