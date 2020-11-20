package com.pactera.esc.aspect;

import com.pactera.esc.core.common.BaseErrorCode;
import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Optional;

/**
 * 异常拦截器
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class ExceptionAspect {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<JsonResult> handleConstraintViolationException(BindException e) {
        BindingResult result = e.getBindingResult();
        log.error("Internal Server Error", e);
        return ResponseEntity.ok(toJsonResult(result));
    }

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<JsonResult> handleBaseException(BaseException e) {
        log.error("Internal Server Error", e);
        return ResponseEntity.ok(JsonResult.failed(e.getErrorCode(), e.getErrorMessage(), e.getMessage()));
    }

    @ExceptionHandler(ItemNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<JsonResult> handleItemNotFoundException(ItemNotFoundException e) {
        log.error("Bad Request", e);
        return ResponseEntity.ok(JsonResult.failed(e.getErrorCode(), e.getErrorMessage(), e.getMessage()));
    }

    @ExceptionHandler(NotLoginException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<JsonResult> handleNotLoginException(NotLoginException e) {
        log.error("Not login", e);
        return ResponseEntity.ok(JsonResult.failed(e.getErrorCode(), e.getErrorMessage(), e.getMessage()));
    }

    @ExceptionHandler(ItemRepeatException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<JsonResult> handleItemRepeatException(ItemRepeatException e) {
        log.error("Item Repeat", e);
        return ResponseEntity.ok(JsonResult.failed(e.getErrorCode(), e.getErrorMessage(), e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<JsonResult> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("Method Argument Not Valid", e);
        BindingResult result = e.getBindingResult();
        return ResponseEntity.ok(toJsonResult(result));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<JsonResult> handleException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.ok(JsonResult.failed(e.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<JsonResult> handleBusinessException(BusinessException e) {
        return ResponseEntity.ok(JsonResult.failed(e.getErrorCode(), e.getErrorMessage(), e.getMessage()));
    }

    private JsonResult toJsonResult(BindingResult result) {
        FieldError fieldError = result.getFieldError();
        String desc = Optional.ofNullable(fieldError).map(FieldError::getDefaultMessage).orElse(null);
        return JsonResult.failed(BaseErrorCode.INVALID_PARAMETERS.getCode(), BaseErrorCode.INVALID_PARAMETERS.getMessage(), desc);
    }
}
