package com.pactera.esc.core.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pactera.esc.core.util.DateUtil;
import lombok.Data;

import java.time.LocalDateTime;
@Data
public class OrganizationVO {
    private String id;

    private String name;

    private String address;

    private String phone;

    private String supplierCode;

    @JsonFormat(pattern = DateUtil.DATE_FORMAT_DEFAULT)
    private LocalDateTime createTime;
}
