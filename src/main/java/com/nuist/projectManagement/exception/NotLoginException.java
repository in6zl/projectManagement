package com.nuist.projectManagement.exception;

import com.nuist.projectManagement.common.BaseErrorCode;

public class NotLoginException extends BaseException {
    public NotLoginException(String description) {
        super(BaseErrorCode.AUTHENTICATE_FAILURE, description);
    }
}
