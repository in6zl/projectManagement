package com.pactera.esc.core.dto.security;

import com.pactera.esc.core.domain.Resource;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

/**
 * AuthorityDTO
 * @author yayun
 * @date 2019/08/07
 */
@Data
@NoArgsConstructor
public class AuthorityDTO implements GrantedAuthority {
    private static final long serialVersionUID = 2889101803949428816L;

    private String permission;

    @Override
    public String getAuthority() {
        return permission;
    }

    public AuthorityDTO(String permission) {
        this.permission = permission;
    }

    public AuthorityDTO(Resource resource) {
        this.permission = resource.getPermission();
    }
}
