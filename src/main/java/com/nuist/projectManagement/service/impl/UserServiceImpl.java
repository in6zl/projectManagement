package com.nuist.projectManagement.service.impl;

import com.nuist.projectManagement.dto.UserDTO;
import com.nuist.projectManagement.dto.map.UserMap;
import com.nuist.projectManagement.mapper.UserMapper;
import com.nuist.projectManagement.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author zl
 * @Date 2020/10/13 14:28
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserMap userMap;

    @Override
    public void addUser(UserDTO userDTO) {
        userMapper.insertSelective(userMap.toEntity(userDTO));
    }
}
