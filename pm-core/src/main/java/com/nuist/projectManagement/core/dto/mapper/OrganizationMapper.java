package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Organization;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.dto.OrganizationDTO;
import com.pactera.esc.core.vo.OrganizationVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author si.chen
 * @date 2020/3/27 16:39
 */
@Mapper(componentModel = "spring")
public interface OrganizationMapper extends BaseMapper<Organization, OrganizationDTO> {

    @Override
    @Mappings({
        @Mapping(source = "roles", target = "roles",qualifiedByName = "dto"),
    })
    OrganizationDTO toDTO(Organization entity);

    @Override
    @Mappings({
        @Mapping(source = "roles", target = "roles",ignore = true),
    })
    Organization toEntity(OrganizationDTO organizationDTO);

    OrganizationVO toVO(Organization organization);

    @Named("dto")
    default String[] dto(Set<Role> roles) {
        List<String> r=new ArrayList<>();
        for(Role role:roles){
            r.add(role.getId());
        }
        String[] s=new String[r.size()];
        r.toArray(s);
        return s;
    }
}
