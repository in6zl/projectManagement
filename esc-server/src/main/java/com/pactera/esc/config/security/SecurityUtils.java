package com.pactera.esc.config.security;

import com.pactera.esc.core.dao.UserRepository;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.security.UserDetailsDTO;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.core.exception.NotLoginException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author yayun
 */
@Component
public class SecurityUtils {
    @Resource
    private UserRepository userRepository;

    public User getLoginUser() throws BaseException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return   Optional.ofNullable(authentication)
                .map(Authentication::getPrincipal)
                .map(ud -> ((UserDetailsDTO)ud).getUsername())
                .flatMap(username -> userRepository.findUserByUsername(username))
                .orElseThrow(() -> new NotLoginException("Please Login"));
    }
}
