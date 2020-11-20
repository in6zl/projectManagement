package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class WarehouseMapperImpl implements BaseMapper<Warehouse, WarehouseDTO> {

    @Override
    public Warehouse toEntity(WarehouseDTO d) {
        if ( d == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( d.getCreator() != null ) {
            warehouse.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            warehouse.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            warehouse.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            warehouse.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            warehouse.setId( d.getId() );
        }
        if ( d.getName() != null ) {
            warehouse.setName( d.getName() );
        }
        if ( d.getArea() != null ) {
            warehouse.setArea( d.getArea() );
        }
        if ( d.getAddress() != null ) {
            warehouse.setAddress( d.getAddress() );
        }
        if ( d.getLongitude() != null ) {
            warehouse.setLongitude( d.getLongitude() );
        }
        if ( d.getLatitude() != null ) {
            warehouse.setLatitude( d.getLatitude() );
        }
        if ( d.getType() != null ) {
            warehouse.setType( d.getType() );
        }
        if ( d.getIsFull() != null ) {
            warehouse.setIsFull( d.getIsFull() );
        }
        if ( d.getIsOpen() != null ) {
            warehouse.setIsOpen( d.getIsOpen() );
        }
        if ( d.getPageable() != null ) {
            warehouse.setPageable( d.getPageable() );
        }

        return warehouse;
    }

    @Override
    public WarehouseDTO toDTO(Warehouse e) {
        if ( e == null ) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        if ( e.getId() != null ) {
            warehouseDTO.setId( e.getId() );
        }
        if ( e.getName() != null ) {
            warehouseDTO.setName( e.getName() );
        }
        if ( e.getArea() != null ) {
            warehouseDTO.setArea( e.getArea() );
        }
        if ( e.getAddress() != null ) {
            warehouseDTO.setAddress( e.getAddress() );
        }
        if ( e.getLongitude() != null ) {
            warehouseDTO.setLongitude( e.getLongitude() );
        }
        if ( e.getLatitude() != null ) {
            warehouseDTO.setLatitude( e.getLatitude() );
        }
        if ( e.getType() != null ) {
            warehouseDTO.setType( e.getType() );
        }
        if ( e.getIsFull() != null ) {
            warehouseDTO.setIsFull( e.getIsFull() );
        }
        if ( e.getIsOpen() != null ) {
            warehouseDTO.setIsOpen( e.getIsOpen() );
        }
        if ( e.getCreator() != null ) {
            warehouseDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            warehouseDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            warehouseDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            warehouseDTO.setUpdateTime( e.getUpdateTime() );
        }
        if ( e.getPageable() != null ) {
            warehouseDTO.setPageable( e.getPageable() );
        }

        return warehouseDTO;
    }
}
