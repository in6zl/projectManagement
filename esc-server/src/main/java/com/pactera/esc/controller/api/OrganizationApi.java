package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.dto.OrganizationDTO;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author minhao.cai
 * @date 2020/03/30
 */
@Api(tags = "组织 API")
@RequestMapping("/api/organization")
public interface OrganizationApi {

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
     * @param organization organization
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询单个-按条件", notes = "入参:id,name,rootId,type <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> find(@RequestBody Organization organization) throws BaseException;

    /**
     * 查询列表
     *
     * @param organization organization
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询列表", notes = "入参:id,name,rootId,type <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> list(@RequestBody Organization organization) throws BaseException;

    /**
     * 查询分页
     *
     * @param organization organization
     * @return 消息体
     * @throws BaseException 基本异常
     */
    @ApiOperation(value = "查询分页", notes = "入参:id,name,rootId,type <br>" +
        "分页和排序：pageParam.pageNo,pageParam.pageSize,sortParam.field,sortParam.direction <br>" +
        "验证规则：无 <br>", response = JsonResult.class)
    ResponseEntity<JsonResult> page(@RequestBody Organization organization) throws BaseException;

    /**
     * 创建组织
     *
     * @param organization
     * @return
     * @throws BaseException
     */
    @ApiOperation(value = "创建组织", notes = "入参:name,type", response = JsonResult.class)
    @PostMapping("/create")
    JsonResult create(@ApiParam(name = "organization", value = "组织", required = true)
                      @RequestBody SaveOrganizationDTO organization);

    /**
     * 更新
     *
     * @param organization organization
     * @return
     */
    @ApiOperation(value = "更新组织", notes = "更新组织")
    @PutMapping("/update")
    JsonResult update(@ApiParam(name = "organization", value = "组织", required = true) @RequestBody SaveOrganizationDTO organization);

    /**
     * 删除组织
     *
     * @param organizationId organizationId
     * @return
     */
    @ApiOperation(value = "删除组织", notes = "删除组织")
    @DeleteMapping("/delete/{organizationId}")
    ResponseEntity<JsonResult> delete(@ApiParam(name = "organizationId", value = "组织id") @PathVariable String organizationId);

    @ApiOperation(value = "查看是否存在相同名称的组织", notes = "查看是否存在相同名称的组织")
    @PostMapping("/findByName")
    ResponseEntity<JsonResult> findByName(@RequestBody OrganizationDTO organizationDTO) throws BaseException;

}
