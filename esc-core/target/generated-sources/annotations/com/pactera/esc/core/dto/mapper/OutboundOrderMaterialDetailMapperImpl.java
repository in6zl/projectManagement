package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class OutboundOrderMaterialDetailMapperImpl implements OutboundOrderMaterialDetailMapper {

    @Override
    public OutboundOrderMaterialDetail toEntity(OutboundOrderMaterialDetailDTO d) {
        if ( d == null ) {
            return null;
        }

        OutboundOrderMaterialDetail outboundOrderMaterialDetail = new OutboundOrderMaterialDetail();

        outboundOrderMaterialDetail.setId( d.getId() );
        outboundOrderMaterialDetail.setNum( d.getNum() );

        return outboundOrderMaterialDetail;
    }

    @Override
    public OutboundOrderMaterialDetailDTO toDTO(OutboundOrderMaterialDetail entity) {
        if ( entity == null ) {
            return null;
        }

        OutboundOrderMaterialDetailDTO outboundOrderMaterialDetailDTO = new OutboundOrderMaterialDetailDTO();

        outboundOrderMaterialDetailDTO.setMaterialName( entityMaterialName( entity ) );
        outboundOrderMaterialDetailDTO.setBinId( entityBinId( entity ) );
        outboundOrderMaterialDetailDTO.setUnitName( entityMaterialUnitName( entity ) );
        outboundOrderMaterialDetailDTO.setBinCode( entityBinCode( entity ) );
        outboundOrderMaterialDetailDTO.setMaterialCode( entityMaterialCode( entity ) );
        outboundOrderMaterialDetailDTO.setMaterialId( entityMaterialId( entity ) );
        outboundOrderMaterialDetailDTO.setOutboundOrderMaterialId( entityOutboundOrderMaterialId( entity ) );
        outboundOrderMaterialDetailDTO.setId( entity.getId() );
        outboundOrderMaterialDetailDTO.setNum( entity.getNum() );

        return outboundOrderMaterialDetailDTO;
    }

    private String entityMaterialName(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = outboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String name = material.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityBinId(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        StorageBin bin = outboundOrderMaterialDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String id = bin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityMaterialUnitName(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = outboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String unitName = material.getUnitName();
        if ( unitName == null ) {
            return null;
        }
        return unitName;
    }

    private String entityBinCode(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        StorageBin bin = outboundOrderMaterialDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String code = bin.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityMaterialCode(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = outboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String code = material.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityMaterialId(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = outboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String id = material.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityOutboundOrderMaterialId(OutboundOrderMaterialDetail outboundOrderMaterialDetail) {
        if ( outboundOrderMaterialDetail == null ) {
            return null;
        }
        OutboundOrderMaterial outboundOrderMaterial = outboundOrderMaterialDetail.getOutboundOrderMaterial();
        if ( outboundOrderMaterial == null ) {
            return null;
        }
        String id = outboundOrderMaterial.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
