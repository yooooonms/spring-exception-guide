package com.example.exception.domain.member.exception;

import com.example.exception.domain.value.Email;
import com.example.exception.global.error.exception.ErrorCode;
import com.example.exception.global.error.exception.InvalidValueException;

public class EmailDuplicatedException extends InvalidValueException {

    public EmailDuplicatedException(final Email email) {
        super(email.getValue(), ErrorCode.EMAIL_DUPLICATION);
    }

}
