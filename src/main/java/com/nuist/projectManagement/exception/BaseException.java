package com.nuist.projectManagement.exception;

import com.nuist.projectManagement.common.BaseErrorCode;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public BaseException(String errorCode, String errorMessage) {
        this(errorCode, errorMessage, errorMessage);
    }

    public BaseException(BaseErrorCode baseErrorCode) {
        this(baseErrorCode, baseErrorCode.getMessage());
    }

    public BaseException(BaseErrorCode baseErrorCode, String desc) {
        this(baseErrorCode.getCode(), baseErrorCode.getMessage(), desc);
    }

    public BaseException(String errorCode, String errorMessage, String desc) {
        super(desc);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
