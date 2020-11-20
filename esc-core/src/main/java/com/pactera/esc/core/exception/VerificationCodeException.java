package com.pactera.esc.core.exception;

import org.springframework.security.core.AuthenticationException;

public class VerificationCodeException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public VerificationCodeException(String msg) {
        super(msg);
    }

}
