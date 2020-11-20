package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictTypeDTO;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 陸文
 * @date 2020/3/2613:54
 */
@Api(tags = "字典类型 API")
@RequestMapping("/api/dicttype")
public interface DictTypeApi {

    /**
     * 创建字典类型
     * @param dictType dictType
     * @return ResponseEntity<JsonResult>
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "字典类型", notes = "字典类型")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Success"),
        @ApiResponse(code = 400, message = "Bad Request"),
        @ApiResponse(code = 401, message = "Unauthorized"),
        @ApiResponse(code = 403, message = "Forbidden"),
        @ApiResponse(code = 404, message = "Not Found"),
        @ApiResponse(code = 500, message = "Server Internal Error")
    })
    @PostMapping("/add")
    ResponseEntity<JsonResult> addDictType(@ApiParam(name = "dictType", value = "字典类型") @Validated @RequestBody DictType dictType)throws BaseException;


    /**
     * 查询单个-按条件
     *
     * @param dictType dictType
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询单个-按条件", notes = "入参:id,name <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "success", response = JsonResult.class),
        @ApiResponse(code = 400, message = "Bad Request", response = JsonResult.class),
        @ApiResponse(code = 401, message = "Unauthorized", response = JsonResult.class),
        @ApiResponse(code = 404, message = "items: id is not found", response = JsonResult.class),
        @ApiResponse(code = 500, message = "Server Internal Error", response = JsonResult.class)})
    @PostMapping("/find")
    ResponseEntity<JsonResult> find(@RequestBody DictType dictType) throws BaseException;

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
     * 删除字典类型
     * @param id id
     * @return ResponseEntity<JsonResult>
     * @throws BaseException BaseException
     */
    @ApiOperation(value = "删除字典类型", notes = "删除字典类型")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "字典类型ID", required = true, paramType = "path",
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
     * 更新字典类型
     * @param id id
     * @param dictType dictType
     * @return ResponseEntity<JsonResult>
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "更新字典类型", notes = "根据id更新字典类型。验证规则：id不能为空")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "id", value = "字典类型ID", required = true, paramType = "path",
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
    ResponseEntity<JsonResult> update(@PathVariable("id") String id,@RequestBody DictType dictType) throws BaseException;

    /**
     * 查询列表
     *
     * @param dictType dictType
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
    ResponseEntity<JsonResult> list(@RequestBody DictType dictType) throws BaseException;

    /**
     * 查询分页
     *
     * @param dictType dictType
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
    ResponseEntity<JsonResult> page(@RequestBody DictType dictType) throws BaseException;

    @ApiOperation(value = "检验字典类型名称是否重复", notes = "检验字典类型是否重复")
    @PostMapping("/multipleDictType")
    ResponseEntity<JsonResult> multipleDictType(@ApiParam(name = "dictTypeDTO", value = "字典类型") @Validated @RequestBody DictTypeDTO dictTypeDTO) throws BaseException;


}
