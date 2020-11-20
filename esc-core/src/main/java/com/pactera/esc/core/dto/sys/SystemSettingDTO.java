package com.pactera.esc.core.dto.sys;

import lombok.Data;

@Data
public class SystemSettingDTO {
    private String id;

    private Integer frozenRequire;

    private Integer frozenTransfer;

    private String address;

    private String location;
}
