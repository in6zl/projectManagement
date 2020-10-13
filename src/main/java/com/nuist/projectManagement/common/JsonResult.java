package com.nuist.projectManagement.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 王亚运 on 2019/4/14.
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult<T> {

    public static final String CODE_SUCCESS = "0";
    public static final String CODE_FAILED = "-1";
    public static final String CODE_NOT_LOGIN = "-2";
    private String code;
    private String message;
    private String description;
    private T data;

    private JsonResult(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private JsonResult(String code, String message, String description, T data) {
        this.code = code;
        this.message = message;
        this.description = description;
        this.data = data;
    }

    public static <T> JsonResult<T> ok(T data) {
        return new JsonResult<>(CODE_SUCCESS, "success", null, data);
    }

    public static <T> JsonResult<T> ok() {
        return JsonResult.ok(null);
    }

    public static <T> JsonResult<T> failed(String message) {
        return failed("", message);
    }

    public static <T> JsonResult<T> failed(BaseErrorCode baseErrorCode) {
        return new JsonResult<>(baseErrorCode.getCode(), baseErrorCode.getMessage());
    }

    public static <T> JsonResult<T> failed(BaseErrorCode baseErrorCode, String description) {
        return new JsonResult<>(baseErrorCode.getCode(), baseErrorCode.getMessage(), description, null);
    }

    public static <T> JsonResult<T> failed(String errorCode, String message) {
        return new JsonResult<>(errorCode, message, null, null);
    }

    public static <T> JsonResult<T> failed(String errorCode, String message, String description) {
        return new JsonResult<>(errorCode, message, description, null);
    }
}
