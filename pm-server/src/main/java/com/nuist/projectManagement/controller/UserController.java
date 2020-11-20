package com.pactera.esc.controller;

import com.pactera.esc.config.security.SecurityUtils;
import com.pactera.esc.controller.api.UserApi;
import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.UserDTO;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.sys.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author si.chen
 * @date 2020/3/30 14:07
 */
@RequestMapping("api/user")
@RestController
@AllArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;

    private SecurityUtils securityUtils;

    @Override
    @GetMapping("{id}")
    public ResponseEntity<JsonResult> detail(@PathVariable("id") String id) throws BaseException {
        UserDTO res = userService.detail(id);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("find")
    public ResponseEntity<JsonResult> find(@RequestBody User user) throws BaseException {
        UserDTO res = userService.find(user);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("list")
    public ResponseEntity<JsonResult> list(@RequestBody User user) throws BaseException {
        List<UserDTO> res = userService.list(user);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("page")
    @PreAuthorize("hasAuthority('user:list')")
    public ResponseEntity<JsonResult> page(@RequestBody User user) throws BaseException {
        User loginUser = securityUtils.getLoginUser();
        Page<UserDTO> res = userService.page(user,loginUser);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PostMapping("create")
    @PreAuthorize("hasAuthority('user:add')")
    public ResponseEntity<JsonResult> create(UserDTO userDTO) throws BaseException {
        userService.create(userDTO);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @PutMapping("update")
    @PreAuthorize("hasAuthority('user:edit')")
    public ResponseEntity<JsonResult> update(UserDTO userDTO) throws BaseException {
        userService.update(userDTO);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('user:delete')")
    public ResponseEntity<JsonResult> delete(@PathVariable("id") String id) throws BaseException {
        userService.delete(id);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @GetMapping("/currentUser")
    public ResponseEntity<JsonResult> currentUser() throws BaseException {
        User user = securityUtils.getLoginUser();
        return ResponseEntity.ok(JsonResult.ok(userService.currentUser(user)));
    }

    @Override
    @PutMapping("updatePassword")
    public ResponseEntity<JsonResult> updatePassword(@Validated({Constants.CheckId.class, Constants.Password.class})
                                                     @RequestBody User user) throws BaseException {
        userService.updatePassword(user);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @PutMapping("resetPassword/{id}")
    public ResponseEntity<JsonResult> resetPassword(@PathVariable("id") String id) throws BaseException {
        userService.resetPassword(id);
        return ResponseEntity.ok(JsonResult.ok());
    }
}
