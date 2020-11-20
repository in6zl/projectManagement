package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Resource;
import com.pactera.esc.core.dto.ResourceDTO;
import com.pactera.esc.core.dto.security.AuthorityDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-20T13:51:33+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ResourceMapperImpl implements ResourceMapper {

    @Override
    public Resource toEntity(ResourceDTO d) {
        if ( d == null ) {
            return null;
        }

        Resource resource = new Resource();

        resource.setId( d.getId() );
        resource.setName( d.getName() );
        if ( d.getType() != null ) {
            resource.setType( d.getType().intValue() );
        }
        resource.setUrl( d.getUrl() );
        resource.setPermission( d.getPermission() );
        resource.setLevel( d.getLevel() );
        resource.setSort( d.getSort() );
        resource.setParentId( d.getParentId() );
        resource.setIcon( d.getIcon() );
        resource.setTitle( d.getTitle() );

        return resource;
    }

    @Override
    public ResourceDTO toDTO(Resource e) {
        if ( e == null ) {
            return null;
        }

        ResourceDTO resourceDTO = new ResourceDTO();

        resourceDTO.setId( e.getId() );
        resourceDTO.setName( e.getName() );
        if ( e.getType() != null ) {
            resourceDTO.setType( e.getType().byteValue() );
        }
        resourceDTO.setUrl( e.getUrl() );
        resourceDTO.setPermission( e.getPermission() );
        resourceDTO.setIcon( e.getIcon() );
        resourceDTO.setLevel( e.getLevel() );
        resourceDTO.setSort( e.getSort() );
        resourceDTO.setParentId( e.getParentId() );
        resourceDTO.setTitle( e.getTitle() );

        return resourceDTO;
    }

    @Override
    public AuthorityDTO toAuthority(Resource resource) {
        if ( resource == null ) {
            return null;
        }

        AuthorityDTO authorityDTO = new AuthorityDTO();

        authorityDTO.setPermission( resource.getPermission() );

        return authorityDTO;
    }
}
