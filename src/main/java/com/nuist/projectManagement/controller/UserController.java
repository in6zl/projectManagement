package com.nuist.projectManagement.controller;

import com.nuist.projectManagement.common.JsonResult;
import com.nuist.projectManagement.dto.UserDTO;
import com.nuist.projectManagement.exception.BaseException;
import com.nuist.projectManagement.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author zl
 * @Date 2020/10/13 14:50
 */
@Api(tags = "用户信息 API")
@RequestMapping("api/user")
@RestController
public class UserController {
    @Resource
    private UserService userService;

    @ApiOperation(value = "添加用户信息", notes = "添加用户信息")
    @PostMapping("/create")
    public ResponseEntity<JsonResult> create(@ApiParam(name = "user", value = "用户信息表") @Validated @RequestBody UserDTO userDTO) throws BaseException {
        userService.addUser(userDTO);
        return ResponseEntity.ok(JsonResult.ok());
    }
}

