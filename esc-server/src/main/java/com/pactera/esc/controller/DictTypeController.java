package com.pactera.esc.controller;

import com.pactera.esc.controller.api.DictTypeApi;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictTypeDTO;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.sys.service.DictTypeService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陸文
 * @date 2020/3/2613:54
 */
@RestController
public class DictTypeController implements DictTypeApi {

    private final DictTypeService dictTypeService;

    public DictTypeController(DictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    @Override
    @PreAuthorize("hasAuthority('dictType:add')")
    public ResponseEntity<JsonResult> addDictType(DictType dictType) {
        try {
            dictTypeService.create(dictType);
            return ResponseEntity.ok(JsonResult.ok());
        } catch (Exception e) {
            return ResponseEntity.ok(JsonResult.failed("-1", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<JsonResult> find(DictType dictType) throws BaseException {
        DictType res = dictTypeService.find(dictType);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    public ResponseEntity<JsonResult> get(String id) throws BaseException {
        DictType res = dictTypeService.get(id);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PreAuthorize("hasAuthority('dictType:delete')")
    public ResponseEntity<JsonResult> delete(String id) throws BaseException {
        dictTypeService.delete(id);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @PreAuthorize("hasAuthority('dictType:edit')")
    public ResponseEntity<JsonResult> update(String id, DictType dictType) throws BaseException {
        try {
            dictTypeService.update(dictType);
            return ResponseEntity.ok(JsonResult.ok());
        } catch (Exception e) {
            return ResponseEntity.ok(JsonResult.failed("-1", e.getMessage()));
        }
    }

    @Override
    public ResponseEntity<JsonResult> list(DictType dictType) throws BaseException {
        List<DictTypeDTO> res = dictTypeService.list(dictType);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PreAuthorize("hasAuthority('dictType:list')")
    public ResponseEntity<JsonResult> page(DictType dictType) throws BaseException {
        Page<DictTypeDTO> res = dictTypeService.page(dictType);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    public ResponseEntity<JsonResult> multipleDictType(DictTypeDTO dictTypeDTO) throws BaseException {
        try{
            dictTypeService.multipleDictType(dictTypeDTO);
            return ResponseEntity.ok(JsonResult.ok());
        }catch (Exception e) {
            return ResponseEntity.ok(JsonResult.failed("01","字典类型名称重复"));
        }
    }

}
