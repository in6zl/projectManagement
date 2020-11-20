package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class WorkShopMapperImpl implements WorkShopMapper {

    @Override
    public WorkShopDTO toDTO(WorkShop entity) {
        if ( entity == null ) {
            return null;
        }

        WorkShopDTO workShopDTO = new WorkShopDTO();

        if ( entity.getId() != null ) {
            workShopDTO.setId( entity.getId() );
        }
        if ( entity.getName() != null ) {
            workShopDTO.setName( entity.getName() );
        }
        if ( entity.getAddress() != null ) {
            workShopDTO.setAddress( entity.getAddress() );
        }
        if ( entity.getManager() != null ) {
            workShopDTO.setManager( entity.getManager() );
        }
        if ( entity.getPhone() != null ) {
            workShopDTO.setPhone( entity.getPhone() );
        }

        return workShopDTO;
    }

    @Override
    public WorkShop toEntity(WorkShopDTO dto) {
        if ( dto == null ) {
            return null;
        }

        WorkShop workShop = new WorkShop();

        if ( dto.getId() != null ) {
            workShop.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            workShop.setName( dto.getName() );
        }
        if ( dto.getAddress() != null ) {
            workShop.setAddress( dto.getAddress() );
        }
        if ( dto.getManager() != null ) {
            workShop.setManager( dto.getManager() );
        }
        if ( dto.getPhone() != null ) {
            workShop.setPhone( dto.getPhone() );
        }

        return workShop;
    }
}
