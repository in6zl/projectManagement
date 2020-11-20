package com.pactera.esc.core.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 陸文
 * @title: sysDictTypeDTO
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2610:34
 */
@Data
public class DictTypeDTO implements Serializable {

    private static final long serialVersionUID = 3796659677049787389L;

    private String id;

    private String name;

    private String creator;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
