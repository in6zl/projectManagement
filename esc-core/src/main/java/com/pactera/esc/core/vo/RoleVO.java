package com.pactera.esc.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pactera.esc.core.util.DateUtil;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class RoleVO {
    private String id;

    private String name;

    private String description;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT_DEFAULT)
    private LocalDateTime createTime;
}
