package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.security.AuthorityDTO;
import org.mapstruct.Mapper;

/**
 * @author si.chen
 * @date 2020/3/27 15:41
 */
@Mapper(componentModel = "spring")
public interface ResourceMapper extends BaseMapper<Resource, ResourceDTO> {
    AuthorityDTO toAuthority(Resource resource);
}
