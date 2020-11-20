package com.pactera.esc.controller;

import com.pactera.esc.common.util.FileUploadUtils;
import com.pactera.esc.controller.api.CommonApi;
import com.pactera.esc.core.common.ApplicationProperties;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 陸文
 * @date 2019/7/511:04
 */
@RestController
public class CommonController implements CommonApi {
    @Resource
    private ApplicationProperties applicationProperties;

    @Override
    public JsonResult<String> upload(HttpServletRequest request, HttpServletResponse response)
            throws BaseException, IOException {
        ApplicationProperties.UploadProperties properties = applicationProperties.getUpload();
        String result = FileUploadUtils.upload(request, response, properties.getFilePath(),
            properties.getWebPath());
        return JsonResult.ok(result);
    }
}
