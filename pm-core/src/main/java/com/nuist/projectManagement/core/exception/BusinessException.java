package com.pactera.esc.core.exception;

import com.pactera.esc.core.common.BaseErrorCode;

/**
 * 业务报错提示（http：200）
 *
 * @author si.chen
 * @date 2020/5/28 10:20
 */
public class BusinessException extends BaseException {

    public BusinessException(BaseErrorCode baseErrorCode) {
        super(baseErrorCode);
    }

}
