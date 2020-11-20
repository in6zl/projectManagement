package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.DictType;
import com.pactera.esc.core.dto.DictTypeDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class DictTypeMapperImpl implements DictTypeMapper {

    @Override
    public DictType toEntity(DictTypeDTO d) {
        if ( d == null ) {
            return null;
        }

        DictType dictType = new DictType();

        if ( d.getCreator() != null ) {
            dictType.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            dictType.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            dictType.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            dictType.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            dictType.setId( d.getId() );
        }
        if ( d.getName() != null ) {
            dictType.setName( d.getName() );
        }

        return dictType;
    }

    @Override
    public DictTypeDTO toDTO(DictType e) {
        if ( e == null ) {
            return null;
        }

        DictTypeDTO dictTypeDTO = new DictTypeDTO();

        if ( e.getId() != null ) {
            dictTypeDTO.setId( e.getId() );
        }
        if ( e.getName() != null ) {
            dictTypeDTO.setName( e.getName() );
        }
        if ( e.getCreator() != null ) {
            dictTypeDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            dictTypeDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            dictTypeDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            dictTypeDTO.setUpdateTime( e.getUpdateTime() );
        }

        return dictTypeDTO;
    }
}
