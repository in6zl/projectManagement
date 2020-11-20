package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.domain.Role;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.RoleDTO;
import com.pactera.esc.core.vo.RoleVO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDTO d) {
        if ( d == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( d.getId() );
        role.setName( d.getName() );
        role.setDescription( d.getDescription() );
        role.setResources( resourceDTOListToResourceList( d.getResources() ) );

        return role;
    }

    @Override
    public RoleDTO toDTO(Role e) {
        if ( e == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( e.getId() );
        roleDTO.setName( e.getName() );
        roleDTO.setDescription( e.getDescription() );
        roleDTO.setResources( resourceListToResourceDTOList( e.getResources() ) );

        return roleDTO;
    }

    @Override
    public RoleVO toVO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleVO roleVO = new RoleVO();

        roleVO.setId( role.getId() );
        roleVO.setName( role.getName() );
        roleVO.setDescription( role.getDescription() );
        roleVO.setCreateTime( role.getCreateTime() );

        return roleVO;
    }

    @Override
    public void update(Role oldRole, RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return;
        }

        if ( roleDTO.getId() != null ) {
            oldRole.setId( roleDTO.getId() );
        }
        if ( roleDTO.getName() != null ) {
            oldRole.setName( roleDTO.getName() );
        }
        if ( roleDTO.getDescription() != null ) {
            oldRole.setDescription( roleDTO.getDescription() );
        }
        if ( oldRole.getResources() != null ) {
            List<Resource> list = resourceDTOListToResourceList( roleDTO.getResources() );
            if ( list != null ) {
                oldRole.getResources().clear();
                oldRole.getResources().addAll( list );
            }
        }
        else {
            List<Resource> list = resourceDTOListToResourceList( roleDTO.getResources() );
            if ( list != null ) {
                oldRole.setResources( list );
            }
        }
    }

    protected Resource resourceDTOToResource(ResourceDTO resourceDTO) {
        if ( resourceDTO == null ) {
            return null;
        }

        Resource resource = new Resource();

        resource.setId( resourceDTO.getId() );
        resource.setName( resourceDTO.getName() );
        if ( resourceDTO.getType() != null ) {
            resource.setType( resourceDTO.getType().intValue() );
        }
        resource.setUrl( resourceDTO.getUrl() );
        resource.setPermission( resourceDTO.getPermission() );
        resource.setLevel( resourceDTO.getLevel() );
        resource.setSort( resourceDTO.getSort() );
        resource.setParentId( resourceDTO.getParentId() );
        resource.setIcon( resourceDTO.getIcon() );
        resource.setTitle( resourceDTO.getTitle() );

        return resource;
    }

    protected List<Resource> resourceDTOListToResourceList(List<ResourceDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Resource> list1 = new ArrayList<Resource>( list.size() );
        for ( ResourceDTO resourceDTO : list ) {
            list1.add( resourceDTOToResource( resourceDTO ) );
        }

        return list1;
    }

    protected ResourceDTO resourceToResourceDTO(Resource resource) {
        if ( resource == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( resource.getId() );
        resourceDTO.setName( resource.getName() );
        if ( resource.getType() != null ) {
            resourceDTO.setType( resource.getType().byteValue() );
        }
        resourceDTO.setUrl( resource.getUrl() );
        resourceDTO.setPermission( resource.getPermission() );
        resourceDTO.setIcon( resource.getIcon() );
        resourceDTO.setLevel( resource.getLevel() );
        resourceDTO.setSort( resource.getSort() );
        resourceDTO.setParentId( resource.getParentId() );
        resourceDTO.setTitle( resource.getTitle() );

        return resourceDTO;
    }

    protected List<ResourceDTO> resourceListToResourceDTOList(List<Resource> list) {
        if ( list == null ) {
            return null;
        }

        List<ResourceDTO> list1 = new ArrayList<ResourceDTO>( list.size() );
        for ( Resource resource : list ) {
            list1.add( resourceToResourceDTO( resource ) );
        }

        return list1;
    }
}
