package com.pactera.esc.core.dto.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * UserDetailsDTO
 *
 * @author yayun
 * @date 2019/08/07
 */
@Data
public class UserDetailsDTO implements UserDetails {
    private static final long serialVersionUID = 8427908071693022059L;
    private String id;
    private String username;
    private String password;
    private Set<AuthorityDTO> authorities;
    private Boolean isAccountNonLocked;
    private Boolean isRememberMe;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
