package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InboundOrderMaterialDetailMapperImpl implements InboundOrderMaterialDetailMapper {

    @Override
    public InboundOrderMaterialDetail toEntity(InboundOrderMaterialDetailDTO d) {
        if ( d == null ) {
            return null;
        }

        InboundOrderMaterialDetail inboundOrderMaterialDetail = new InboundOrderMaterialDetail();

        inboundOrderMaterialDetail.setId( d.getId() );
        if ( d.getNum() != null ) {
            inboundOrderMaterialDetail.setNum( d.getNum().doubleValue() );
        }
        inboundOrderMaterialDetail.setBatch( d.getBatch() );

        return inboundOrderMaterialDetail;
    }

    @Override
    public InboundOrderMaterialDetailDTO toDTO(InboundOrderMaterialDetail entity) {
        if ( entity == null ) {
            return null;
        }

        InboundOrderMaterialDetailDTO inboundOrderMaterialDetailDTO = new InboundOrderMaterialDetailDTO();

        inboundOrderMaterialDetailDTO.setMaterialName( entityMaterialName( entity ) );
        inboundOrderMaterialDetailDTO.setBinId( entityBinId( entity ) );
        inboundOrderMaterialDetailDTO.setInboundOrderMaterialId( entityInboundOrderMaterialId( entity ) );
        inboundOrderMaterialDetailDTO.setBinCode( entityBinCode( entity ) );
        inboundOrderMaterialDetailDTO.setMaterialCode( entityMaterialCode( entity ) );
        inboundOrderMaterialDetailDTO.setMaterialId( entityMaterialId( entity ) );
        inboundOrderMaterialDetailDTO.setId( entity.getId() );
        if ( entity.getNum() != null ) {
            inboundOrderMaterialDetailDTO.setNum( entity.getNum().intValue() );
        }
        inboundOrderMaterialDetailDTO.setBatch( entity.getBatch() );

        return inboundOrderMaterialDetailDTO;
    }

    private String entityMaterialName(InboundOrderMaterialDetail inboundOrderMaterialDetail) {
        if ( inboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = inboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String name = material.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String entityBinId(InboundOrderMaterialDetail inboundOrderMaterialDetail) {
        if ( inboundOrderMaterialDetail == null ) {
            return null;
        }
        StorageBin bin = inboundOrderMaterialDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String id = bin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityInboundOrderMaterialId(InboundOrderMaterialDetail inboundOrderMaterialDetail) {
        if ( inboundOrderMaterialDetail == null ) {
            return null;
        }
        InboundOrderMaterial inboundOrderMaterial = inboundOrderMaterialDetail.getInboundOrderMaterial();
        if ( inboundOrderMaterial == null ) {
            return null;
        }
        String id = inboundOrderMaterial.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityBinCode(InboundOrderMaterialDetail inboundOrderMaterialDetail) {
        if ( inboundOrderMaterialDetail == null ) {
            return null;
        }
        StorageBin bin = inboundOrderMaterialDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String code = bin.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityMaterialCode(InboundOrderMaterialDetail inboundOrderMaterialDetail) {
        if ( inboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = inboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String code = material.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityMaterialId(InboundOrderMaterialDetail inboundOrderMaterialDetail) {
        if ( inboundOrderMaterialDetail == null ) {
            return null;
        }
        Material material = inboundOrderMaterialDetail.getMaterial();
        if ( material == null ) {
            return null;
        }
        String id = material.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
