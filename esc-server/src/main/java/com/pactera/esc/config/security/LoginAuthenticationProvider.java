package com.pactera.esc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public class LoginAuthenticationProvider extends DaoAuthenticationProvider {

    @Autowired
    private HttpServletRequest httpServletRequest;

    public LoginAuthenticationProvider() {
        super();
    }


    @Override
    public Authentication authenticate(Authentication authentication) {
        LoginAuthenticationToken token = (LoginAuthenticationToken) authentication;
        try {
            return super.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw e;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
