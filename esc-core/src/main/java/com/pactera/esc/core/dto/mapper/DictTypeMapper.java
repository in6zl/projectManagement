package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;

/**
 * @author 陸文
 * @date 2020/3/2610:37
 */
@Mapper(componentModel = "spring", nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface DictTypeMapper extends BaseMapper<DictType, DictTypeDTO> {
}
