package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.User;
import com.pactera.esc.core.dto.UserDTO;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.*;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author si.chen
 * @date 2020/3/30 13:36
 */
@Api(tags = "用户API")
public interface UserApi {

    /**
     * 查询单个-按主键
     *
     * @param id id
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询单个-按主键", notes = "入参:id <br>" +
        "验证规则：id非空 <br>", response = JsonResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path",
            dataType = "String")})
    ResponseEntity<JsonResult> detail(@PathVariable("id") String id) throws BaseException;

    /**
     * 查询单个-按条件
     *
     * @param user user
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询单个-按条件", notes = "入参:id,username,password,organization.id <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> find(@RequestBody User user) throws BaseException;

    /**
     * 查询列表
     *
     * @param user user
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询列表", notes = "入参:id,username,password,organization.id <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> list(@RequestBody User user) throws BaseException;

    /**
     * 查询分页
     *
     * @param user user
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询分页", notes = "入参:id,username,password,organization.id <br>" +
        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> page(@RequestBody User user) throws BaseException;

    /**
     * 新增
     *
     * @param userDTO userDTO
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "新增", notes = "入参：username,organization.id,realName,password,email,phone,type,address,imagePath,enable <br>" +
        "验证规则：username,realName,password,type,organization.id非空 <br>",
        response = JsonResult.class)
    ResponseEntity<JsonResult> create(@RequestBody
                                      @NonNull UserDTO userDTO) throws BaseException;

    /**
     * 修改
     *
     * @param userDTO userDTO
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "修改", notes = "修改")
    ResponseEntity<JsonResult> update(@RequestBody
                                      @NonNull UserDTO userDTO) throws BaseException;

    /**
     * 删除
     *
     * @param id id
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "删除", notes = "入参:id <br>" +
        "验证规则：id非空 <br>", response = JsonResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path",
            dataType = "String")})
    ResponseEntity<JsonResult> delete(@PathVariable("id") String id) throws BaseException;

    /**
     * 获取当前用户
     *
     * @return
     * @throws BaseException
     */
    @ApiOperation(value = "当前用户", notes = "当前用户", response = JsonResult.class)
    ResponseEntity<JsonResult> currentUser() throws BaseException;

    /**
     * 修改密码
     *
     * @param user user
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "修改密码", notes = "入参：id,oldPassword,password <br>" +
        "验证规则：id,oldPassword,password非空 <br>",
        response = JsonResult.class)
    ResponseEntity<JsonResult> updatePassword(@Validated({Constants.CheckId.class, Constants.Password.class})
                                              @RequestBody User user) throws BaseException;

    /**
     * 重置密码
     *
     * @param id id
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "重置密码", notes = "入参:id <br>" +
        "验证规则：id非空 <br>", response = JsonResult.class)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "id", required = true, paramType = "path",
            dataType = "String")})
    ResponseEntity<JsonResult> resetPassword(@PathVariable("id") String id) throws BaseException;
}
