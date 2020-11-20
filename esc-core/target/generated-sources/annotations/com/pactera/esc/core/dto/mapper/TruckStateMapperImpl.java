package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class TruckStateMapperImpl implements TruckStateMapper {

    @Override
    public TruckStateDTO toDTO(TruckState entity) {
        if ( entity == null ) {
            return null;
        }

        TruckStateDTO truckStateDTO = new TruckStateDTO();

        String id = entityWarehouseId( entity );
        if ( id != null ) {
            truckStateDTO.setWarehouseId( id );
        }
        String name = entityWarehouseName( entity );
        if ( name != null ) {
            truckStateDTO.setWarehouseName( name );
        }
        if ( entity.getId() != null ) {
            truckStateDTO.setId( entity.getId() );
        }
        if ( entity.getName() != null ) {
            truckStateDTO.setName( entity.getName() );
        }
        if ( entity.getStatus() != null ) {
            truckStateDTO.setStatus( entity.getStatus() );
        }
        if ( entity.getTime() != null ) {
            truckStateDTO.setTime( entity.getTime() );
        }
        if ( entity.getCarNo() != null ) {
            truckStateDTO.setCarNo( entity.getCarNo() );
        }

        return truckStateDTO;
    }

    @Override
    public TruckState toEntity(TruckStateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        TruckState truckState = new TruckState();

        truckState.setWarehouse( truckStateDTOToWarehouse( dto ) );
        if ( dto.getId() != null ) {
            truckState.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            truckState.setName( dto.getName() );
        }
        if ( dto.getStatus() != null ) {
            truckState.setStatus( dto.getStatus() );
        }
        if ( dto.getTime() != null ) {
            truckState.setTime( dto.getTime() );
        }
        if ( dto.getCarNo() != null ) {
            truckState.setCarNo( dto.getCarNo() );
        }

        return truckState;
    }

    private String entityWarehouseId(TruckState truckState) {
        if ( truckState == null ) {
            return null;
        }
        Warehouse warehouse = truckState.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseName(TruckState truckState) {
        if ( truckState == null ) {
            return null;
        }
        Warehouse warehouse = truckState.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String name = warehouse.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Warehouse truckStateDTOToWarehouse(TruckStateDTO truckStateDTO) {
        if ( truckStateDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( truckStateDTO.getWarehouseId() != null ) {
            warehouse.setId( truckStateDTO.getWarehouseId() );
        }
        if ( truckStateDTO.getWarehouseName() != null ) {
            warehouse.setName( truckStateDTO.getWarehouseName() );
        }

        return warehouse;
    }
}
