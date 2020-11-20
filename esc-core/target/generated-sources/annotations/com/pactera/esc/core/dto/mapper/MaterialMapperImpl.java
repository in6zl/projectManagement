package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class MaterialMapperImpl implements BaseMapper<Material, MaterialDTO> {

    @Override
    public Material toEntity(MaterialDTO d) {
        if ( d == null ) {
            return null;
        }

        Material material = new Material();

        if ( d.getId() != null ) {
            material.setId( d.getId() );
        }
        if ( d.getCode() != null ) {
            material.setCode( d.getCode() );
        }
        if ( d.getName() != null ) {
            material.setName( d.getName() );
        }
        if ( d.getSpec() != null ) {
            material.setSpec( d.getSpec() );
        }
        if ( d.getUnitName() != null ) {
            material.setUnitName( d.getUnitName() );
        }
        if ( d.getIsDelete() != null ) {
            material.setIsDelete( d.getIsDelete() );
        }

        return material;
    }

    @Override
    public MaterialDTO toDTO(Material e) {
        if ( e == null ) {
            return null;
        }

        MaterialDTO materialDTO = new MaterialDTO();

        if ( e.getId() != null ) {
            materialDTO.setId( e.getId() );
        }
        if ( e.getName() != null ) {
            materialDTO.setName( e.getName() );
        }
        if ( e.getCode() != null ) {
            materialDTO.setCode( e.getCode() );
        }
        if ( e.getSpec() != null ) {
            materialDTO.setSpec( e.getSpec() );
        }
        if ( e.getUnitName() != null ) {
            materialDTO.setUnitName( e.getUnitName() );
        }
        if ( e.getIsDelete() != null ) {
            materialDTO.setIsDelete( e.getIsDelete() );
        }

        return materialDTO;
    }
}
