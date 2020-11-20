package com.pactera.esc.core.exception;

import com.pactera.esc.core.common.BaseErrorCode;

public class ItemNotFoundException extends BaseException {

    public ItemNotFoundException() {
        super(BaseErrorCode.ITEM_NOT_FOUND);
    }

    public ItemNotFoundException(String description) {
        super(BaseErrorCode.ITEM_NOT_FOUND, description);
    }
}
