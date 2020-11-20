package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.vo.RoleVO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * @author si.chen
 * @date 2020/3/27 15:41
 */
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper extends BaseMapper<Role, RoleDTO> {

    RoleVO toVO(Role role);

    void update(@MappingTarget Role oldRole, RoleDTO roleDTO);
}
