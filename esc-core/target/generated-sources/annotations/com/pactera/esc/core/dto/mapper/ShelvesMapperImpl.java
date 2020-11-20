package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ShelvesMapperImpl implements ShelvesMapper {

    @Override
    public ShelvesDTO toDTO(Shelves entity) {
        if ( entity == null ) {
            return null;
        }

        ShelvesDTO shelvesDTO = new ShelvesDTO();

        String id = entityWarehouseId( entity );
        if ( id != null ) {
            shelvesDTO.setWarehouseId( id );
        }
        String name = entityWarehouseName( entity );
        if ( name != null ) {
            shelvesDTO.setWarehouseName( name );
        }
        if ( entity.getId() != null ) {
            shelvesDTO.setId( entity.getId() );
        }
        if ( entity.getCode() != null ) {
            shelvesDTO.setCode( entity.getCode() );
        }
        if ( entity.getCreator() != null ) {
            shelvesDTO.setCreator( entity.getCreator() );
        }
        if ( entity.getLayers() != null ) {
            shelvesDTO.setLayers( entity.getLayers() );
        }
        if ( entity.getOperator() != null ) {
            shelvesDTO.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            shelvesDTO.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getUpdateTime() != null ) {
            shelvesDTO.setUpdateTime( entity.getUpdateTime() );
        }
        if ( entity.getPageable() != null ) {
            shelvesDTO.setPageable( entity.getPageable() );
        }

        return shelvesDTO;
    }

    @Override
    public Shelves toEntity(ShelvesDTO entity) {
        if ( entity == null ) {
            return null;
        }

        Shelves shelves = new Shelves();

        shelves.setWarehouse( shelvesDTOToWarehouse( entity ) );
        if ( entity.getCreator() != null ) {
            shelves.setCreator( entity.getCreator() );
        }
        if ( entity.getOperator() != null ) {
            shelves.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            shelves.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getUpdateTime() != null ) {
            shelves.setUpdateTime( entity.getUpdateTime() );
        }
        if ( entity.getId() != null ) {
            shelves.setId( entity.getId() );
        }
        if ( entity.getCode() != null ) {
            shelves.setCode( entity.getCode() );
        }
        if ( entity.getLayers() != null ) {
            shelves.setLayers( entity.getLayers() );
        }
        if ( entity.getPageable() != null ) {
            shelves.setPageable( entity.getPageable() );
        }

        return shelves;
    }

    private String entityWarehouseId(Shelves shelves) {
        if ( shelves == null ) {
            return null;
        }
        Warehouse warehouse = shelves.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseName(Shelves shelves) {
        if ( shelves == null ) {
            return null;
        }
        Warehouse warehouse = shelves.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String name = warehouse.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Warehouse shelvesDTOToWarehouse(ShelvesDTO shelvesDTO) {
        if ( shelvesDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( shelvesDTO.getWarehouseId() != null ) {
            warehouse.setId( shelvesDTO.getWarehouseId() );
        }
        if ( shelvesDTO.getWarehouseName() != null ) {
            warehouse.setName( shelvesDTO.getWarehouseName() );
        }

        return warehouse;
    }
}
