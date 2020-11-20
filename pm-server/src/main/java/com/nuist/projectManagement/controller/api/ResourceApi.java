package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author si.chen
 * @date 2020/3/30 15:03
 */
@Api(tags = "权限资源API")
public interface ResourceApi {
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
     * @param resource resource
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询单个-按条件", notes = "入参:id,name,parentId,level,type <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> find(@RequestBody Resource resource) throws BaseException;

    /**
     * 查询列表
     *
     * @param resource resource
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询列表", notes = "入参:id,name,parentId,level,type <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> list(@RequestBody Resource resource) throws BaseException;

    /**
     * 查询分页
     *
     * @param resource resource
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询分页", notes = "入参:id,name,parentId,level,type <br>" +
        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> page(@RequestBody Resource resource) throws BaseException;

    /**
     * 新增
     *
     * @param resource resource
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "新增", notes = "入参：name,type,url,permission,level,sort,parentId <br>" +
        "验证规则：name,type,parentId非空 <br>",
        response = JsonResult.class)
    ResponseEntity<JsonResult> create(@Validated
                                      @RequestBody Resource resource) throws BaseException;

    /**
     * 修改
     *
     * @param resource resource
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "修改", notes = "入参：id,name,type,url,permission,level,sort,parentId <br>" +
        "验证规则：id非空 <br>",
        response = JsonResult.class)
    ResponseEntity<JsonResult> update(@Validated({Constants.CheckId.class})
                                      @RequestBody Resource resource) throws BaseException;

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
     * 根据父id获取
     *
     * @param parentId
     * @return
     * @throws BaseException
     */
    @ApiOperation("根据父id获取")
    @GetMapping("/getResourceByParentId/{parentId}")
    ResponseEntity<JsonResult> getResourceByParentId(@PathVariable("parentId") String parentId);

    /**
     * 获取所有2级菜单
     * @return
     */
    @ApiOperation("获取所有2级菜单")
    @GetMapping("/getSecondLevelResource")
    ResponseEntity<JsonResult> getSecondLevelResource();


    //------------------角色-资源-----------------------

//    /**
//     * 查询分页
//     *
//     * @param roleResource roleResource
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "查询分页", notes = "入参:resourceId,roleId <br>" +
//        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
//        "验证规则：无 <br>", response = JsonResult.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "success", response = JsonResult.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = JsonResult.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = JsonResult.class),
//        @ApiResponse(code = 404, message = "items: id is not found", response = JsonResult.class),
//        @ApiResponse(code = 500, message = "Server Internal Error", response = JsonResult.class)})
//    ResponseEntity<JsonResult> pageRoleResource(@RequestBody RoleResource roleResource) throws BaseException;
//
//    /**
//     * 新增
//     *
//     * @param roleResource roleResource
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "新增", notes = "入参：resourceId,roleId <br>" +
//        "验证规则：resourceId,roleId非空 <br>",
//        response = JsonResult.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "success", response = JsonResult.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = JsonResult.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = JsonResult.class),
//        @ApiResponse(code = 404, message = "items: id is not found", response = JsonResult.class),
//        @ApiResponse(code = 500, message = "Server Internal Error", response = JsonResult.class)})
//    ResponseEntity<JsonResult> createRoleResource(@Validated
//                                                  @RequestBody RoleResource roleResource) throws BaseException;
//
//    /**
//     * 删除
//     *
//     * @param roleResource roleResource
//     * @return 消息体
//     * @throws BaseException 基本异常
//     */
//    @ApiOperation(value = "查询分页", notes = "入参:resourceId,roleId <br>" +
//        "验证规则：无 <br>", response = JsonResult.class)
//    @ApiResponses(value = {
//        @ApiResponse(code = 200, message = "success", response = JsonResult.class),
//        @ApiResponse(code = 400, message = "Bad Request", response = JsonResult.class),
//        @ApiResponse(code = 401, message = "Unauthorized", response = JsonResult.class),
//        @ApiResponse(code = 404, message = "items: id is not found", response = JsonResult.class),
//        @ApiResponse(code = 500, message = "Server Internal Error", response = JsonResult.class)})
//    ResponseEntity<JsonResult> deleteRoleResource(@RequestBody RoleResource roleResource) throws BaseException;
}
