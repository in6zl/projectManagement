package com.pactera.esc.core.dto;

import com.pactera.esc.core.domain.DictType;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 陸文
 * @title: DictDTO
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2713:41
 */
@Data
public class DictDTO implements Serializable {

    private static final long serialVersionUID = 509750497821633296L;

    private String id;

    private String dictKey;

    private String dictValue;

    private DictType dictType;

    private String creator;

    private String operator;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

}
