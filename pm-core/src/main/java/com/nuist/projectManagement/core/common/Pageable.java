package com.pactera.esc.core.common;

import lombok.*;

/**
 * @author si.chen
 * @date 2019/7/10 16:52
 */
@Getter
@Setter
public class Pageable {

    private Integer pageNo = Constants.PAGE_NUM;

    private Integer pageSize = Constants.PAGE_SIZE;
}
