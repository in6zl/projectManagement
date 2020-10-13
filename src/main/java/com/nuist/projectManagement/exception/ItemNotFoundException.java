package com.nuist.projectManagement.exception;

import com.nuist.projectManagement.common.BaseErrorCode;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException() {
        super(BaseErrorCode.ITEM_NOT_FOUND);
    }

    public ItemNotFoundException(String description) {
        super(BaseErrorCode.ITEM_NOT_FOUND, description);
    }
}
