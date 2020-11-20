package com.pactera.esc.core.dto.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InboundOrderMaterialMapperImpl implements InboundOrderMaterialMapper {

    @Override
    public InboundOrderMaterialDTO toDTO(InboundOrderMaterial entity) {
        if ( entity == null ) {
            return null;
        }

        InboundOrderMaterialDTO inboundOrderMaterialDTO = new InboundOrderMaterialDTO();

        inboundOrderMaterialDTO.setInvoiceId( entityInvoiceId( entity ) );
        inboundOrderMaterialDTO.setId( entity.getId() );
        inboundOrderMaterialDTO.setCode( entity.getCode() );
        inboundOrderMaterialDTO.setInvoice( entity.getInvoice() );
        inboundOrderMaterialDTO.setWarehouse( warehouseToWarehouseDTO( entity.getWarehouse() ) );
        inboundOrderMaterialDTO.setInOperator( entity.getInOperator() );
        inboundOrderMaterialDTO.setDeliveryMan( entity.getDeliveryMan() );
        inboundOrderMaterialDTO.setInTime( entity.getInTime() );
        inboundOrderMaterialDTO.setIsFinished( entity.getIsFinished() );
        List<InboundOrderMaterialDetail> list = entity.getDetailList();
        if ( list != null ) {
            inboundOrderMaterialDTO.setDetailList( new ArrayList<InboundOrderMaterialDetail>( list ) );
        }
        inboundOrderMaterialDTO.setSearchStartTime( entity.getSearchStartTime() );
        inboundOrderMaterialDTO.setSearchEndTime( entity.getSearchEndTime() );

        return inboundOrderMaterialDTO;
    }

    @Override
    public InboundOrderMaterial toEntity(InboundOrderMaterialDTO dto) {
        if ( dto == null ) {
            return null;
        }

        InboundOrderMaterial inboundOrderMaterial = new InboundOrderMaterial();

        inboundOrderMaterial.setInvoice( inboundOrderMaterialDTOToInvoice( dto ) );
        inboundOrderMaterial.setId( dto.getId() );
        inboundOrderMaterial.setCode( dto.getCode() );
        inboundOrderMaterial.setWarehouse( warehouseDTOToWarehouse( dto.getWarehouse() ) );
        inboundOrderMaterial.setInOperator( dto.getInOperator() );
        inboundOrderMaterial.setDeliveryMan( dto.getDeliveryMan() );
        inboundOrderMaterial.setInTime( dto.getInTime() );
        inboundOrderMaterial.setIsFinished( dto.getIsFinished() );
        List<InboundOrderMaterialDetail> list = dto.getDetailList();
        if ( list != null ) {
            inboundOrderMaterial.setDetailList( new ArrayList<InboundOrderMaterialDetail>( list ) );
        }
        inboundOrderMaterial.setSearchStartTime( dto.getSearchStartTime() );
        inboundOrderMaterial.setSearchEndTime( dto.getSearchEndTime() );

        return inboundOrderMaterial;
    }

    private String entityInvoiceId(InboundOrderMaterial inboundOrderMaterial) {
        if ( inboundOrderMaterial == null ) {
            return null;
        }
        Invoice invoice = inboundOrderMaterial.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String id = invoice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        warehouseDTO.setId( warehouse.getId() );
        warehouseDTO.setName( warehouse.getName() );
        warehouseDTO.setArea( warehouse.getArea() );
        warehouseDTO.setAddress( warehouse.getAddress() );
        warehouseDTO.setLongitude( warehouse.getLongitude() );
        warehouseDTO.setLatitude( warehouse.getLatitude() );
        warehouseDTO.setType( warehouse.getType() );
        warehouseDTO.setIsFull( warehouse.getIsFull() );
        warehouseDTO.setIsOpen( warehouse.getIsOpen() );
        warehouseDTO.setCreator( warehouse.getCreator() );
        warehouseDTO.setOperator( warehouse.getOperator() );
        warehouseDTO.setCreateTime( warehouse.getCreateTime() );
        warehouseDTO.setUpdateTime( warehouse.getUpdateTime() );
        warehouseDTO.setPageable( warehouse.getPageable() );

        return warehouseDTO;
    }

    protected Invoice inboundOrderMaterialDTOToInvoice(InboundOrderMaterialDTO inboundOrderMaterialDTO) {
        if ( inboundOrderMaterialDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( inboundOrderMaterialDTO.getInvoiceId() );

        return invoice;
    }

    protected Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO) {
        if ( warehouseDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.setCreator( warehouseDTO.getCreator() );
        warehouse.setOperator( warehouseDTO.getOperator() );
        warehouse.setCreateTime( warehouseDTO.getCreateTime() );
        warehouse.setUpdateTime( warehouseDTO.getUpdateTime() );
        warehouse.setId( warehouseDTO.getId() );
        warehouse.setName( warehouseDTO.getName() );
        warehouse.setArea( warehouseDTO.getArea() );
        warehouse.setAddress( warehouseDTO.getAddress() );
        warehouse.setLongitude( warehouseDTO.getLongitude() );
        warehouse.setLatitude( warehouseDTO.getLatitude() );
        warehouse.setType( warehouseDTO.getType() );
        warehouse.setIsFull( warehouseDTO.getIsFull() );
        warehouse.setIsOpen( warehouseDTO.getIsOpen() );
        warehouse.setPageable( warehouseDTO.getPageable() );

        return warehouse;
    }
}
