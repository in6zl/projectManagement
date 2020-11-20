package com.pactera.esc.core.dto.sys.mapper;

import com.pactera.esc.core.domain.SystemSetting;
import com.pactera.esc.core.dto.mapper.BaseMapper;
import com.pactera.esc.core.dto.sys.SystemSettingDTO;
import com.pactera.esc.core.vo.SystemSettingVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author yayun
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SystemSettingMapper extends BaseMapper<SystemSetting, SystemSettingDTO> {
    SystemSettingVO toVO(SystemSetting systemSetting);

    void update(@MappingTarget SystemSetting db, SystemSettingDTO systemSetting);
}
