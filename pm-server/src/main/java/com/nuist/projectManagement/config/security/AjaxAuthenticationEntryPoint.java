package com.pactera.esc.config.security;

import com.pactera.esc.common.ResponseWrapper;
import com.pactera.esc.core.common.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        JsonResult result = JsonResult.failed(JsonResult.CODE_NOT_LOGIN, "Not login");
        ResponseWrapper.jsonResult(response, HttpStatus.UNAUTHORIZED, result);
    }
}
