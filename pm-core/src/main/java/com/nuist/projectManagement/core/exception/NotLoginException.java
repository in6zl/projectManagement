package com.pactera.esc.core.exception;

import com.pactera.esc.core.common.BaseErrorCode;

public class NotLoginException extends BaseException {
    public NotLoginException(String description) {
        super(BaseErrorCode.AUTHENTICATE_FAILURE, description);
    }
}
