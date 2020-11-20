package com.pactera.esc.core.vo;

import lombok.Data;

import javax.persistence.Column;
@Data
public class SystemSettingVO {
    private String id;

    private Integer frozenRequire;

    private Integer frozenTransfer;

    private String address;

    private String location;
}
