package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class OutboundOrderProductMapperImpl implements OutboundOrderProductMapper {

    @Override
    public OutboundOrderProduct toEntity(OutboundOrderProductDTO d) {
        if ( d == null ) {
            return null;
        }

        OutboundOrderProduct outboundOrderProduct = new OutboundOrderProduct();

        outboundOrderProduct.setCreator( d.getCreator() );
        outboundOrderProduct.setOperator( d.getOperator() );
        outboundOrderProduct.setId( d.getId() );
        outboundOrderProduct.setCode( d.getCode() );
        outboundOrderProduct.setOutOperator( d.getOutOperator() );
        outboundOrderProduct.setReceiver( d.getReceiver() );
        outboundOrderProduct.setOutTime( d.getOutTime() );
        outboundOrderProduct.setIsFinished( d.getIsFinished() );

        return outboundOrderProduct;
    }

    @Override
    public OutboundOrderProductDTO toDTO(OutboundOrderProduct entity) {
        if ( entity == null ) {
            return null;
        }

        OutboundOrderProductDTO outboundOrderProductDTO = new OutboundOrderProductDTO();

        outboundOrderProductDTO.setOutProductOrderNo( entityOutProductOrderOrderNo( entity ) );
        outboundOrderProductDTO.setOutProductOrderOutOperator( entityOutProductOrderOutOperator( entity ) );
        outboundOrderProductDTO.setWarehouseId( entityWarehouseId( entity ) );
        outboundOrderProductDTO.setWarehouseName( entityWarehouseName( entity ) );
        outboundOrderProductDTO.setOutProductOrderId( entityOutProductOrderId( entity ) );
        outboundOrderProductDTO.setId( entity.getId() );
        outboundOrderProductDTO.setCode( entity.getCode() );
        outboundOrderProductDTO.setOutOperator( entity.getOutOperator() );
        outboundOrderProductDTO.setReceiver( entity.getReceiver() );
        outboundOrderProductDTO.setCreator( entity.getCreator() );
        outboundOrderProductDTO.setOperator( entity.getOperator() );
        outboundOrderProductDTO.setOutTime( entity.getOutTime() );
        outboundOrderProductDTO.setIsFinished( entity.getIsFinished() );

        return outboundOrderProductDTO;
    }

    private String entityOutProductOrderOrderNo(OutboundOrderProduct outboundOrderProduct) {
        if ( outboundOrderProduct == null ) {
            return null;
        }
        OutProductOrder outProductOrder = outboundOrderProduct.getOutProductOrder();
        if ( outProductOrder == null ) {
            return null;
        }
        String orderNo = outProductOrder.getOrderNo();
        if ( orderNo == null ) {
            return null;
        }
        return orderNo;
    }

    private String entityOutProductOrderOutOperator(OutboundOrderProduct outboundOrderProduct) {
        if ( outboundOrderProduct == null ) {
            return null;
        }
        OutProductOrder outProductOrder = outboundOrderProduct.getOutProductOrder();
        if ( outProductOrder == null ) {
            return null;
        }
        String outOperator = outProductOrder.getOutOperator();
        if ( outOperator == null ) {
            return null;
        }
        return outOperator;
    }

    private String entityWarehouseId(OutboundOrderProduct outboundOrderProduct) {
        if ( outboundOrderProduct == null ) {
            return null;
        }
        Warehouse warehouse = outboundOrderProduct.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseName(OutboundOrderProduct outboundOrderProduct) {
        if ( outboundOrderProduct == null ) {
            return null;
        }
        Warehouse warehouse = outboundOrderProduct.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String name = warehouse.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityOutProductOrderId(OutboundOrderProduct outboundOrderProduct) {
        if ( outboundOrderProduct == null ) {
            return null;
        }
        OutProductOrder outProductOrder = outboundOrderProduct.getOutProductOrder();
        if ( outProductOrder == null ) {
            return null;
        }
        String id = outProductOrder.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
