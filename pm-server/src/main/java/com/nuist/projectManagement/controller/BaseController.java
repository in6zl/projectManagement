package com.pactera.esc.controller;

import com.pactera.esc.core.common.Constants;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.util.BuildIdFactory;
import org.apache.commons.collections.MapUtils;
import org.springframework.http.ResponseEntity;

import java.util.Map;

/**
 * @author zmj
 * @description
 * @date 2020/4/17 0017
 */
public abstract class BaseController {

    ResponseEntity<JsonResult<?>> getResult(Map<String,Object> map, String successMessage){
        JsonResult<?> result;
        if(MapUtils.getInteger(map, Constants.RESULT)>0){
            result = JsonResult.ok(successMessage);
        }else{
            result = JsonResult.failed(MapUtils.getString(map,Constants.MESSAGE));
        }
        return ResponseEntity.ok(result);
    }

    ResponseEntity<JsonResult<?>> getNumber(){
        String id = BuildIdFactory.getId();
        return ResponseEntity.ok(JsonResult.ok(id));
    }
}
