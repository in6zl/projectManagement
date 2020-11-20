package com.pactera.esc.config.security;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@Slf4j
public class LoginAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private static final long serialVersionUID = 511L;

    public LoginAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
