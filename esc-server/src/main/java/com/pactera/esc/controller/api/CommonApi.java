package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陸文
 * @date 2019/7/511:00
 */
@RequestMapping("/api/common")
@Api(description = "common")
public interface CommonApi {

    /**
     * 上传文件
     * @param request  request
     * @param response response
     * @return ResponseEntity<JsonResult>
     * @throws BaseException 异常
     * @throws IOException   异常
     */
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "成功返回", response = String.class),
            @ApiResponse(code = 400, message = "Bad Request", response = String.class),
            @ApiResponse(code = 401, message = "Unauthorized", response = String.class),
            @ApiResponse(code = 404, message = "Not Find", response = String.class),
            @ApiResponse(code = 500, message = "Server Internal Error", response = String.class)})
    @PostMapping(value = "/upload")
    JsonResult<String> upload(HttpServletRequest request, HttpServletResponse response) throws BaseException, IOException;
}
