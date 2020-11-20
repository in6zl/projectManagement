package com.pactera.esc.core.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

/**
 * @author si.chen
 * @date 2019/7/16 15:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortParam {

    private String field = "createTime";

    private Sort.Direction direction = Sort.Direction.ASC;
}
