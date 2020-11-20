package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.dto.DictDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author 陸文
 * @title: DictMapper
 * @projectName end-supply-chain
 * @description: TODO
 * @date 2020/3/2713:45
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DictMapper  extends BaseMapper<Dict, DictDTO>{
}
