package com.pactera.esc.common;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.util.JsonUtil;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseWrapper {
    public static void jsonResult(HttpServletResponse response, HttpStatus status, JsonResult result) throws IOException {
        response.setStatus(status.value());
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JsonUtil.jsonObj2Sting(result));
        out.flush();
        out.close();
    }
}
