package com.nuist.projectManagement.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author zl
 * @Date 2020/10/13 14:28
 */
@Data
public class UserDTO {

    private int id;

    private String username;

    private String password;

    private String email;

    private String address;

    private String phone;
}
