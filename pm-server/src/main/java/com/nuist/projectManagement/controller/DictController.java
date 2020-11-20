package com.pactera.esc.controller;

import com.pactera.esc.controller.api.DictApi;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.dto.DictDTO;
import com.pactera.esc.core.exception.BaseException;
import com.pactera.esc.sys.service.DictService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 陸文
 * @title: DictController
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2714:38
 */
@RestController
public class DictController implements DictApi {

    private final DictService dictService;

    public DictController(DictService dictService) {
        this.dictService = dictService;
    }


    @Override
    @PreAuthorize("hasAuthority('dict:add')")
    public ResponseEntity<JsonResult> addDict(Dict dict) throws BaseException {
        dictService.create(dict);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    @PreAuthorize("hasAuthority('dict:delete')")
    public ResponseEntity<JsonResult> delete(String id) throws BaseException {
        dictService.delete(id);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    public ResponseEntity<JsonResult> get(String id) throws BaseException {
        Dict res = dictService.get(id);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PreAuthorize("hasAuthority('dict:edit')")
    public ResponseEntity<JsonResult> update(String id, Dict dict) throws BaseException {
        dictService.update(dict);
        return ResponseEntity.ok(JsonResult.ok());
    }

    @Override
    public ResponseEntity<JsonResult> list(Dict dict) throws BaseException {
        List<DictDTO> res = dictService.list(dict);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    @PreAuthorize("hasAuthority('dict:list')")
    public ResponseEntity<JsonResult> page(Dict dict) throws BaseException {
        Page<DictDTO> res = dictService.page(dict);
        return ResponseEntity.ok(JsonResult.ok(res));
    }

    @Override
    public ResponseEntity<JsonResult<String>> getValue(String key) {
        return  ResponseEntity.ok(JsonResult.ok(dictService.getValue(key)));
    }

    @Override
    public ResponseEntity<JsonResult> getByDictType(String name) {
        return ResponseEntity.ok(JsonResult.ok(dictService.getByDictType(name)));
    }
}
