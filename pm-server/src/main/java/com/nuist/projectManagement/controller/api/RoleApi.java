package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "角色API")
public interface RoleApi {

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
    ResponseEntity<JsonResult> get(@PathVariable("id") String id) throws BaseException;

    /**
     * 查询单个-按条件
     *
     * @param role role
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询单个-按条件", notes = "入参:id,name <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> find(@RequestBody Role role) throws BaseException;

    /**
     * 查询列表
     *
     * @param role role
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询列表", notes = "入参:id,name <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> list(@RequestBody Role role) throws BaseException;

    /**
     * 查询分页
     *
     * @param role role
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询分页", notes = "入参:id,name <br>" +
        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> page(@RequestBody Role role) throws BaseException;

    /**
     * 新增
     *
     * @param roleDTO roleDTO
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "新增", notes = "入参：name,description <br>" +
        "验证规则：name非空 <br>",
        response = JsonResult.class)
    ResponseEntity<JsonResult> create(@RequestBody RoleDTO roleDTO) throws BaseException;

    /**
     * 修改
     *
     * @param roleDTO roleDTO
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "修改", notes = "修改")
    ResponseEntity<JsonResult> update(@RequestBody
                                      @NonNull RoleDTO roleDTO) throws BaseException;

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

    //-------------------用户-角色---------------------

//    /**
//     * 查询分页
//     *
//     * @param userRole userRole
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "查询分页", notes = "入参:userId,roleId <br>" +
//        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
//        "验证规则：无 <br>", response = JsonResult.class)
//    ResponseEntity<JsonResult> pageUserRole(@RequestBody UserRole userRole) throws BaseException;
//
//    /**
//     * 新增
//     *
//     * @param userRole userRole
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "新增", notes = "入参：userId,roleId <br>" +
//        "验证规则：userId,roleId非空 <br>",
//        response = JsonResult.class)
//    ResponseEntity<JsonResult> createUserRole(@Validated
//                                              @RequestBody UserRole userRole) throws BaseException;
//
//    /**
//     * 删除
//     *
//     * @param userRole userRole
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "查询分页", notes = "入参:userId,roleId <br>" +
//        "验证规则：无 <br>", response = JsonResult.class)
//    ResponseEntity<JsonResult> deleteUserRole(@RequestBody UserRole userRole) throws BaseException;


//    //----------------组织-角色------------------
//
//    /**
//     * 查询分页
//     *
//     * @param organizationRole organizationRole
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "查询分页", notes = "入参:organizationId,roleId <br>" +
//        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
//        "验证规则：无 <br>", response = JsonResult.class)
//    ResponseEntity<JsonResult> pageOrganizationRole(@RequestBody OrganizationRole organizationRole) throws BaseException;
//
//    /**
//     * 新增
//     *
//     * @param organizationRole organizationRole
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "新增", notes = "入参：organizationId,roleId <br>" +
//        "验证规则：organizationId,roleId非空 <br>",
//        response = JsonResult.class)
//    ResponseEntity<JsonResult> createOrganizationRole(@Validated
//                                                      @RequestBody OrganizationRole organizationRole) throws BaseException;
//
//    /**
//     * 删除
//     *
//     * @param organizationRole organizationRole
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "查询分页", notes = "入参:organizationId,roleId <br>" +
//        "验证规则：无 <br>", response = JsonResult.class)
//    ResponseEntity<JsonResult> deleteOrganizationRole(@RequestBody OrganizationRole organizationRole) throws BaseException;

    /**
     * 获取角色树
     * @return ResponseEntity
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "获取角色树", notes = "获取角色树")
    @GetMapping("/getResourceTree")
    JsonResult<List<ResourceDTO>> getResourceTree() throws BaseException;

    /**
     * 获取所有角色（管理员除外）
     *
     * @return ResponseEntity
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "获取所有角色（管理员除外）", notes = "获取所有角色（管理员除外）")
    @GetMapping("/getRolesExclusionAdmin")
    ResponseEntity<JsonResult> getRolesExclusionAdmin() throws BaseException;

    /**
     * 根据组织获取角色
     *
     * @param organizationId organizationId
     * @return ResponseEntity
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "根据组织获取角色", notes = "根据组织获取角色")
    @GetMapping("/getRolesByOrg/{organizationId}")
    ResponseEntity<JsonResult> getRolesByOrg(@PathVariable("organizationId") String organizationId) throws BaseException;

}
