package com.example.exception.domain.member.api;

import com.example.exception.domain.member.application.SignupService;
import com.example.exception.domain.member.dto.MemberReponse;
import com.example.exception.domain.member.dto.SignupRequest;
import com.example.exception.domain.member.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberApi {

    private final SignupService signupService;

    @PostMapping(path = {"/api/v1/signup"})
    public ResponseEntity<MemberReponse> signup(@Valid @RequestBody final SignupRequest dto) {
        log.debug("dto: {}", dto);
        Member member = signupService.signup(dto);
        return ResponseEntity.created(URI.create("/")).body(new MemberReponse(member));
    }

}
