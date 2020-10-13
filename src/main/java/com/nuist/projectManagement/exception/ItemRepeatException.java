package com.nuist.projectManagement.exception;


import com.nuist.projectManagement.common.BaseErrorCode;

public class ItemRepeatException extends BaseException {

    public ItemRepeatException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }

    public ItemRepeatException(String description) {
        super(BaseErrorCode.ITEM_REPEAT, description);
    }
}
