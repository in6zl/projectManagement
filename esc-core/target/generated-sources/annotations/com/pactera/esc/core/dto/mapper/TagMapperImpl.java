package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class TagMapperImpl implements BaseMapper<Tag, TagDTO> {

    @Override
    public Tag toEntity(TagDTO d) {
        if ( d == null ) {
            return null;
        }

        Tag tag = new Tag();

        if ( d.getCreator() != null ) {
            tag.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            tag.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            tag.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            tag.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            tag.setId( d.getId() );
        }
        if ( d.getName() != null ) {
            tag.setName( d.getName() );
        }
        if ( d.getValue() != null ) {
            tag.setValue( d.getValue() );
        }
        if ( d.getPriority() != null ) {
            tag.setPriority( d.getPriority() );
        }

        return tag;
    }

    @Override
    public TagDTO toDTO(Tag e) {
        if ( e == null ) {
            return null;
        }

        TagDTO tagDTO = new TagDTO();

        if ( e.getId() != null ) {
            tagDTO.setId( e.getId() );
        }
        if ( e.getName() != null ) {
            tagDTO.setName( e.getName() );
        }
        if ( e.getCreator() != null ) {
            tagDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            tagDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            tagDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            tagDTO.setUpdateTime( e.getUpdateTime() );
        }
        if ( e.getValue() != null ) {
            tagDTO.setValue( e.getValue() );
        }
        if ( e.getPriority() != null ) {
            tagDTO.setPriority( e.getPriority() );
        }

        return tagDTO;
    }
}
