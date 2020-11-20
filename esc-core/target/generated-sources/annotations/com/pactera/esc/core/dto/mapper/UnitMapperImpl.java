package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class UnitMapperImpl implements BaseMapper<Unit, UnitDTO> {

    @Override
    public Unit toEntity(UnitDTO d) {
        if ( d == null ) {
            return null;
        }

        Unit unit = new Unit();

        if ( d.getId() != null ) {
            unit.setId( d.getId() );
        }
        if ( d.getName() != null ) {
            unit.setName( d.getName() );
        }
        if ( d.getDescription() != null ) {
            unit.setDescription( d.getDescription() );
        }
        if ( d.getIsDelete() != null ) {
            unit.setIsDelete( d.getIsDelete() );
        }

        return unit;
    }

    @Override
    public UnitDTO toDTO(Unit e) {
        if ( e == null ) {
            return null;
        }

        UnitDTO unitDTO = new UnitDTO();

        if ( e.getId() != null ) {
            unitDTO.setId( e.getId() );
        }
        if ( e.getName() != null ) {
            unitDTO.setName( e.getName() );
        }
        if ( e.getDescription() != null ) {
            unitDTO.setDescription( e.getDescription() );
        }
        if ( e.getIsDelete() != null ) {
            unitDTO.setIsDelete( e.getIsDelete() );
        }

        return unitDTO;
    }
}
