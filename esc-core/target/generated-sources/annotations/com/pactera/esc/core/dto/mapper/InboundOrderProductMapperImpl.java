package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-09-01T17:00:21+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InboundOrderProductMapperImpl implements InboundOrderProductMapper {

    @Override
    public InboundOrderProduct toEntity(InboundOrderProductDTO arg0) {
        if ( arg0 == null ) {
            return null;
        }

        InboundOrderProduct inboundOrderProduct = new InboundOrderProduct();

        inboundOrderProduct.setId( arg0.getId() );
        inboundOrderProduct.setCode( arg0.getCode() );
        inboundOrderProduct.setInOperator( arg0.getInOperator() );
        inboundOrderProduct.setDeliveryMan( arg0.getDeliveryMan() );
        inboundOrderProduct.setInTime( arg0.getInTime() );
        inboundOrderProduct.setIsFinished( arg0.getIsFinished() );

        return inboundOrderProduct;
    }

    @Override
    public InboundOrderProductDTO toDTO(InboundOrderProduct entity) {
        if ( entity == null ) {
            return null;
        }

        InboundOrderProductDTO inboundOrderProductDTO = new InboundOrderProductDTO();

        inboundOrderProductDTO.setWarehouseId( entityWarehouseId( entity ) );
        inboundOrderProductDTO.setWarehouseName( entityWarehouseName( entity ) );
        inboundOrderProductDTO.setId( entity.getId() );
        inboundOrderProductDTO.setCode( entity.getCode() );
        inboundOrderProductDTO.setInOperator( entity.getInOperator() );
        inboundOrderProductDTO.setDeliveryMan( entity.getDeliveryMan() );
        inboundOrderProductDTO.setInTime( entity.getInTime() );
        inboundOrderProductDTO.setIsFinished( entity.getIsFinished() );

        return inboundOrderProductDTO;
    }

    private String entityWarehouseId(InboundOrderProduct inboundOrderProduct) {
        if ( inboundOrderProduct == null ) {
            return null;
        }
        Warehouse warehouse = inboundOrderProduct.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseName(InboundOrderProduct inboundOrderProduct) {
        if ( inboundOrderProduct == null ) {
            return null;
        }
        Warehouse warehouse = inboundOrderProduct.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String name = warehouse.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
