package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class MaterialRequisitionDetailMapperImpl implements MaterialRequisitionDetailMapper {

    @Override
    public MaterialRequisitionDetail toEntity(MaterialRequisitionDetailDTO d) {
        if ( d == null ) {
            return null;
        }

        MaterialRequisitionDetail materialRequisitionDetail = new MaterialRequisitionDetail();

        if ( d.getId() != null ) {
            materialRequisitionDetail.setId( d.getId() );
        }
        if ( d.getMaterial() != null ) {
            materialRequisitionDetail.setMaterial( d.getMaterial() );
        }
        if ( d.getNum() != null ) {
            materialRequisitionDetail.setNum( d.getNum() );
        }
        if ( d.getBatch() != null ) {
            materialRequisitionDetail.setBatch( d.getBatch() );
        }
        if ( d.getGetTime() != null ) {
            materialRequisitionDetail.setGetTime( d.getGetTime() );
        }
        if ( d.getWarehouse() != null ) {
            materialRequisitionDetail.setWarehouse( warehouseDTOToWarehouse( d.getWarehouse() ) );
        }

        return materialRequisitionDetail;
    }

    @Override
    public MaterialRequisitionDetailDTO toDTO(MaterialRequisitionDetail materialRequisitionDetail) {
        if ( materialRequisitionDetail == null ) {
            return null;
        }

        MaterialRequisitionDetailDTO materialRequisitionDetailDTO = new MaterialRequisitionDetailDTO();

        if ( materialRequisitionDetail.getId() != null ) {
            materialRequisitionDetailDTO.setId( materialRequisitionDetail.getId() );
        }
        if ( materialRequisitionDetail.getMaterial() != null ) {
            materialRequisitionDetailDTO.setMaterial( materialRequisitionDetail.getMaterial() );
        }
        if ( materialRequisitionDetail.getNum() != null ) {
            materialRequisitionDetailDTO.setNum( materialRequisitionDetail.getNum() );
        }
        if ( materialRequisitionDetail.getBatch() != null ) {
            materialRequisitionDetailDTO.setBatch( materialRequisitionDetail.getBatch() );
        }
        if ( materialRequisitionDetail.getGetTime() != null ) {
            materialRequisitionDetailDTO.setGetTime( materialRequisitionDetail.getGetTime() );
        }
        if ( materialRequisitionDetail.getWarehouse() != null ) {
            materialRequisitionDetailDTO.setWarehouse( warehouseToWarehouseDTO( materialRequisitionDetail.getWarehouse() ) );
        }

        return materialRequisitionDetailDTO;
    }

    protected Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO) {
        if ( warehouseDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        if ( warehouseDTO.getCreator() != null ) {
            warehouse.setCreator( warehouseDTO.getCreator() );
        }
        if ( warehouseDTO.getOperator() != null ) {
            warehouse.setOperator( warehouseDTO.getOperator() );
        }
        if ( warehouseDTO.getCreateTime() != null ) {
            warehouse.setCreateTime( warehouseDTO.getCreateTime() );
        }
        if ( warehouseDTO.getUpdateTime() != null ) {
            warehouse.setUpdateTime( warehouseDTO.getUpdateTime() );
        }
        if ( warehouseDTO.getId() != null ) {
            warehouse.setId( warehouseDTO.getId() );
        }
        if ( warehouseDTO.getName() != null ) {
            warehouse.setName( warehouseDTO.getName() );
        }
        if ( warehouseDTO.getArea() != null ) {
            warehouse.setArea( warehouseDTO.getArea() );
        }
        if ( warehouseDTO.getAddress() != null ) {
            warehouse.setAddress( warehouseDTO.getAddress() );
        }
        if ( warehouseDTO.getLongitude() != null ) {
            warehouse.setLongitude( warehouseDTO.getLongitude() );
        }
        if ( warehouseDTO.getLatitude() != null ) {
            warehouse.setLatitude( warehouseDTO.getLatitude() );
        }
        if ( warehouseDTO.getType() != null ) {
            warehouse.setType( warehouseDTO.getType() );
        }
        if ( warehouseDTO.getIsFull() != null ) {
            warehouse.setIsFull( warehouseDTO.getIsFull() );
        }
        if ( warehouseDTO.getIsOpen() != null ) {
            warehouse.setIsOpen( warehouseDTO.getIsOpen() );
        }
        if ( warehouseDTO.getPageable() != null ) {
            warehouse.setPageable( warehouseDTO.getPageable() );
        }

        return warehouse;
    }

    protected WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        if ( warehouse.getId() != null ) {
            warehouseDTO.setId( warehouse.getId() );
        }
        if ( warehouse.getName() != null ) {
            warehouseDTO.setName( warehouse.getName() );
        }
        if ( warehouse.getArea() != null ) {
            warehouseDTO.setArea( warehouse.getArea() );
        }
        if ( warehouse.getAddress() != null ) {
            warehouseDTO.setAddress( warehouse.getAddress() );
        }
        if ( warehouse.getLongitude() != null ) {
            warehouseDTO.setLongitude( warehouse.getLongitude() );
        }
        if ( warehouse.getLatitude() != null ) {
            warehouseDTO.setLatitude( warehouse.getLatitude() );
        }
        if ( warehouse.getType() != null ) {
            warehouseDTO.setType( warehouse.getType() );
        }
        if ( warehouse.getIsFull() != null ) {
            warehouseDTO.setIsFull( warehouse.getIsFull() );
        }
        if ( warehouse.getIsOpen() != null ) {
            warehouseDTO.setIsOpen( warehouse.getIsOpen() );
        }
        if ( warehouse.getCreator() != null ) {
            warehouseDTO.setCreator( warehouse.getCreator() );
        }
        if ( warehouse.getOperator() != null ) {
            warehouseDTO.setOperator( warehouse.getOperator() );
        }
        if ( warehouse.getCreateTime() != null ) {
            warehouseDTO.setCreateTime( warehouse.getCreateTime() );
        }
        if ( warehouse.getUpdateTime() != null ) {
            warehouseDTO.setUpdateTime( warehouse.getUpdateTime() );
        }
        if ( warehouse.getPageable() != null ) {
            warehouseDTO.setPageable( warehouse.getPageable() );
        }

        return warehouseDTO;
    }
}
