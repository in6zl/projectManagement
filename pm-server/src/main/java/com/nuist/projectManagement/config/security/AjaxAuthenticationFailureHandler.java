package com.pactera.esc.config.security;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.common.ResponseWrapper;
import com.pactera.esc.core.exception.VerificationCodeException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException ex) throws IOException {
        JsonResult result;
        if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
            result = JsonResult.failed(BaseErrorCode.PASSWORD_ERROR.getCode(),"用户名或密码输入错误！");
        } else if (ex instanceof LockedException) {
            result = JsonResult.failed("账户被锁定，请联系管理员！");
        } else if (ex instanceof CredentialsExpiredException) {
            result = JsonResult.failed("密码过期，请联系管理员！");
        } else if (ex instanceof AccountExpiredException) {
            result = JsonResult.failed("账户过期，请联系管理员！");
        } else if (ex instanceof DisabledException) {
            result = JsonResult.failed("账户被禁用，请联系管理员！");
        } else if (ex instanceof VerificationCodeException) {
            result = JsonResult.failed("验证码错误！");
        } else {
            result = JsonResult.failed("登陆失败！");
        }
        ResponseWrapper.jsonResult(response, HttpStatus.UNAUTHORIZED, result);
    }
}
