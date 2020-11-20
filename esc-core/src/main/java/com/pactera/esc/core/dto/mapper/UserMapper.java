package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.UserDTO;
import com.pactera.esc.core.dto.security.AuthorityDTO;
import com.pactera.esc.core.dto.security.UserDetailsDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author si.chen
 * @date 2020/3/26 15:28
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends BaseMapper<User, UserDTO> {
    @Override
    @Mappings({
        @Mapping(source = "organization.id", target = "organizationId"),
        @Mapping(source = "organization.name", target = "organizationName"),
        @Mapping(source = "supplier.id", target = "supplierId"),
        @Mapping(source = "supplier.name", target = "supplierName"),
    })
    UserDTO toDTO(User user);

    @Override
    @Mappings({
        @Mapping(source = "organizationId", target = "organization.id"),
        @Mapping(source = "organizationName", target = "organization.name")
    })
    User toEntity(UserDTO userDTO);

    @Mappings({
        @Mapping(target = "username", source = "user.username"),
        @Mapping(target = "password", source = "user.password"),
        @Mapping(target = "authorities", source = "user.roles", qualifiedByName = "rolesToAuthorities"),
        @Mapping(target = "isAccountNonLocked", source = "isAccountNonLocked"),
    })
    UserDetailsDTO toUserDetails(User user, Boolean isAccountNonLocked);

    @Named("rolesToAuthorities")
    default Set<AuthorityDTO> rolesToAuthorities(List<Role> roles) {
        return roles.stream().flatMap(r -> r.getResources().stream())
            .map(AuthorityDTO::new).collect(Collectors.toSet());
    }
}
