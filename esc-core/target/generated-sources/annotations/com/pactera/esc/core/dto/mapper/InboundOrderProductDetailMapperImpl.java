package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InboundOrderProductDetailMapperImpl implements InboundOrderProductDetailMapper {

    @Override
    public InboundOrderProductDetail toEntity(InboundOrderProductDetailDTO d) {
        if ( d == null ) {
            return null;
        }

        InboundOrderProductDetail inboundOrderProductDetail = new InboundOrderProductDetail();

        inboundOrderProductDetail.setId( d.getId() );
        inboundOrderProductDetail.setNum( d.getNum() );

        return inboundOrderProductDetail;
    }

    @Override
    public InboundOrderProductDetailDTO toDTO(InboundOrderProductDetail entity) {
        if ( entity == null ) {
            return null;
        }

        InboundOrderProductDetailDTO inboundOrderProductDetailDTO = new InboundOrderProductDetailDTO();

        inboundOrderProductDetailDTO.setInboundOrderProductId( entityInboundOrderProductId( entity ) );
        inboundOrderProductDetailDTO.setProductId( entityProductId( entity ) );
        inboundOrderProductDetailDTO.setBinId( entityBinId( entity ) );
        inboundOrderProductDetailDTO.setUnitName( entityProductUnitName( entity ) );
        inboundOrderProductDetailDTO.setBinCode( entityBinCode( entity ) );
        inboundOrderProductDetailDTO.setProductName( entityProductName( entity ) );
        inboundOrderProductDetailDTO.setId( entity.getId() );
        inboundOrderProductDetailDTO.setNum( entity.getNum() );

        return inboundOrderProductDetailDTO;
    }

    private String entityInboundOrderProductId(InboundOrderProductDetail inboundOrderProductDetail) {
        if ( inboundOrderProductDetail == null ) {
            return null;
        }
        InboundOrderProduct inboundOrderProduct = inboundOrderProductDetail.getInboundOrderProduct();
        if ( inboundOrderProduct == null ) {
            return null;
        }
        String id = inboundOrderProduct.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityProductId(InboundOrderProductDetail inboundOrderProductDetail) {
        if ( inboundOrderProductDetail == null ) {
            return null;
        }
        Product product = inboundOrderProductDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityBinId(InboundOrderProductDetail inboundOrderProductDetail) {
        if ( inboundOrderProductDetail == null ) {
            return null;
        }
        StorageBin bin = inboundOrderProductDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String id = bin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityProductUnitName(InboundOrderProductDetail inboundOrderProductDetail) {
        if ( inboundOrderProductDetail == null ) {
            return null;
        }
        Product product = inboundOrderProductDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String unitName = product.getUnitName();
        if ( unitName == null ) {
            return null;
        }
        return unitName;
    }

    private String entityBinCode(InboundOrderProductDetail inboundOrderProductDetail) {
        if ( inboundOrderProductDetail == null ) {
            return null;
        }
        StorageBin bin = inboundOrderProductDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String code = bin.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityProductName(InboundOrderProductDetail inboundOrderProductDetail) {
        if ( inboundOrderProductDetail == null ) {
            return null;
        }
        Product product = inboundOrderProductDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
