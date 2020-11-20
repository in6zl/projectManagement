package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.domain.Dict;
import com.pactera.esc.core.dto.DictDTO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-11-20T13:51:33+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class DictMapperImpl implements DictMapper {

    @Override
    public Dict toEntity(DictDTO d) {
        if ( d == null ) {
            return null;
        }

        Dict dict = new Dict();

        if ( d.getCreator() != null ) {
            dict.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            dict.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            dict.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            dict.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            dict.setId( d.getId() );
        }
        if ( d.getDictKey() != null ) {
            dict.setDictKey( d.getDictKey() );
        }
        if ( d.getDictValue() != null ) {
            dict.setDictValue( d.getDictValue() );
        }
        if ( d.getDictType() != null ) {
            dict.setDictType( d.getDictType() );
        }

        return dict;
    }

    @Override
    public DictDTO toDTO(Dict e) {
        if ( e == null ) {
            return null;
        }

        DictDTO dictDTO = new DictDTO();

        if ( e.getId() != null ) {
            dictDTO.setId( e.getId() );
        }
        if ( e.getDictKey() != null ) {
            dictDTO.setDictKey( e.getDictKey() );
        }
        if ( e.getDictValue() != null ) {
            dictDTO.setDictValue( e.getDictValue() );
        }
        if ( e.getDictType() != null ) {
            dictDTO.setDictType( e.getDictType() );
        }
        if ( e.getCreator() != null ) {
            dictDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            dictDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            dictDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            dictDTO.setUpdateTime( e.getUpdateTime() );
        }

        return dictDTO;
    }
}
