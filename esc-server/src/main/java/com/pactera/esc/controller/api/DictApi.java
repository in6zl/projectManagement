package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 陸文
 * @title: DictApi
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2714:37
 */
@Api(tags = "字典类型 API")
@RequestMapping("/api/dict")
public interface DictApi {

    /**
     * 创建字典表
     * @param dict dict
     * @return ResponseEntity<JsonResult>
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "字典表", notes = "字典表")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
    })
    @PostMapping("/add")
    ResponseEntity<JsonResult> addDict(@ApiParam(name = "dict", value = "字典表") @Validated @RequestBody Dict dict)throws BaseException;

    /**
     * 删除字典表
     * @param id id
     * @return ResponseEntity<JsonResult>
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "删除字典表", notes = "删除字典表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "字典表ID", required = true, paramType = "path",
            dataType = "String")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<JsonResult> delete(@PathVariable("id") String id) throws BaseException;

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
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "success", response = JsonResult.class),
        @ApiResponse(code = 400, message = "Bad Request", response = JsonResult.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = JsonResult.class),
        @ApiResponse(code = 404, message = "items: id is not found", response = JsonResult.class),
        @ApiResponse(code = 500, message = "Server Internal Error", response = JsonResult.class)})
    @GetMapping("/{id}")
    ResponseEntity<JsonResult> get(@PathVariable("id") String id) throws BaseException;

    /**
     * 更新字典表
     * @param id id
     * @param dict dict
     * @return ResponseEntity<JsonResult>
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "更新字典表", notes = "根据id更新字典表。验证规则：id不能为空")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "字典表ID", required = true, paramType = "path",
            dataType = "String")})
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
    })
    @PutMapping("/{id}")
    ResponseEntity<JsonResult> update(@PathVariable("id") String id,@RequestBody Dict dict) throws BaseException;

    /**
     * 查询列表
     *
     * @param dict dict
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询列表", notes = "入参:dictType")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
    })
    @PostMapping("/list")
    ResponseEntity<JsonResult> list(@RequestBody Dict dict) throws BaseException;

    /**
     * 查询分页
     *
     * @param dict dict
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询分页", notes = "入参:userInfo")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
    })
    @PostMapping("/page")
    ResponseEntity<JsonResult> page(@RequestBody Dict dict) throws BaseException;


    /**
     *  获取字典值
     * @param key 字典键
     * @return 字典值
     */
    @ApiOperation(value = "获取字典值", notes = "获取字典值", response = JsonResult.class)
    @GetMapping("/getValue/{key}")
    ResponseEntity<JsonResult<String>> getValue(@ApiParam(name = "字典键")@PathVariable("key") String key);


    @ApiOperation(value = "通过字典类型名称获得字典信息列表", notes = "通过字典类型名称获得字典信息列表", response = JsonResult.class)
    @GetMapping("/DictTypeName/{name}")
    ResponseEntity<JsonResult> getByDictType(@ApiParam(name = "字典类型")@PathVariable("name") String name);
}
