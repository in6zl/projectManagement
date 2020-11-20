package com.pactera.esc.core.exception;

import com.pactera.esc.core.common.BaseErrorCode;

public class ItemRepeatException extends BaseException {

    public ItemRepeatException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }

    public ItemRepeatException(String description) {
        super(BaseErrorCode.ITEM_REPEAT, description);
    }
}
