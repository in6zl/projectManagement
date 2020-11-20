package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class OutboundOrderMaterialMapperImpl implements OutboundOrderMaterialMapper {

    @Override
    public OutboundOrderMaterial toEntity(OutboundOrderMaterialDTO d) {
        if ( d == null ) {
            return null;
        }

        OutboundOrderMaterial outboundOrderMaterial = new OutboundOrderMaterial();

        outboundOrderMaterial.setCreator( d.getCreator() );
        outboundOrderMaterial.setOperator( d.getOperator() );
        outboundOrderMaterial.setId( d.getId() );
        outboundOrderMaterial.setCode( d.getCode() );
        outboundOrderMaterial.setOutOperator( d.getOutOperator() );
        outboundOrderMaterial.setOutTime( d.getOutTime() );
        outboundOrderMaterial.setIsFinished( d.getIsFinished() );

        return outboundOrderMaterial;
    }

    @Override
    public OutboundOrderMaterialDTO toDTO(OutboundOrderMaterial entity) {
        if ( entity == null ) {
            return null;
        }

        OutboundOrderMaterialDTO outboundOrderMaterialDTO = new OutboundOrderMaterialDTO();

        outboundOrderMaterialDTO.setWarehouseId( entityWarehouseId( entity ) );
        outboundOrderMaterialDTO.setWarehouseName( entityWarehouseName( entity ) );
        outboundOrderMaterialDTO.setId( entity.getId() );
        outboundOrderMaterialDTO.setCode( entity.getCode() );
        outboundOrderMaterialDTO.setOutOperator( entity.getOutOperator() );
        outboundOrderMaterialDTO.setOutTime( entity.getOutTime() );
        outboundOrderMaterialDTO.setIsFinished( entity.getIsFinished() );
        outboundOrderMaterialDTO.setCreator( entity.getCreator() );
        outboundOrderMaterialDTO.setOperator( entity.getOperator() );

        return outboundOrderMaterialDTO;
    }

    private String entityWarehouseId(OutboundOrderMaterial outboundOrderMaterial) {
        if ( outboundOrderMaterial == null ) {
            return null;
        }
        Warehouse warehouse = outboundOrderMaterial.getWarehouse();
        if ( warehouse == null ) {
            return null;
        }
        String id = warehouse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityWarehouseName(OutboundOrderMaterial outboundOrderMaterial) {
        if ( outboundOrderMaterial == null ) {
            return null;
        }
        Warehouse warehouse = outboundOrderMaterial.getWarehouse();
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
