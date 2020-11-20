package com.pactera.esc.config.security;

import com.pactera.esc.common.ResponseWrapper;
import com.pactera.esc.core.common.JsonResult;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yayun
 */
public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        JsonResult<String> result = JsonResult.ok(request.getSession().getId());
        ResponseWrapper.jsonResult(response, HttpStatus.OK, result);
    }
}
