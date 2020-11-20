package com.pactera.esc.config.security;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.common.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

class LogoutAuthenticationSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        JsonResult result = JsonResult.failed(JsonResult.CODE_SUCCESS, "success");
        ResponseWrapper.jsonResult(httpServletResponse, HttpStatus.OK, result);
    }
}
