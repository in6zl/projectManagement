package com.pactera.esc.config.security;

import com.pactera.esc.core.dao.ResourceRepository;
import com.pactera.esc.core.dao.UserRepository;
import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.mapper.ResourceMapper;
import com.pactera.esc.core.dto.mapper.UserMapper;
import com.pactera.esc.core.dto.security.UserDetailsDTO;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.pactera.esc.core.common.Constants.UserType.ADMINISTRATOR;

@Component("userDetailsService")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    private final ResourceRepository resourceRepository;
    private final UserMapper userMapper;
    private final ResourceMapper resourceMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(userName).orElseThrow(() -> new UsernameNotFoundException("username not found"));
        UserDetailsDTO details = userMapper.toUserDetails(user, true);
        if(ADMINISTRATOR.getType() != user.getType()) {
            return details;
        }
        List<Resource> resources = resourceRepository.findAll();
        details.setAuthorities(resources.stream().map(resourceMapper::toAuthority).collect(Collectors.toSet()));
        return details;
    }

}
