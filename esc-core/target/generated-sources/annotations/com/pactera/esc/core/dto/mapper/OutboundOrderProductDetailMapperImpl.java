package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class OutboundOrderProductDetailMapperImpl implements OutboundOrderProductDetailMapper {

    @Override
    public OutboundOrderProductDetailDTO toDTO(OutboundOrderProductDetail entity) {
        if ( entity == null ) {
            return null;
        }

        OutboundOrderProductDetailDTO outboundOrderProductDetailDTO = new OutboundOrderProductDetailDTO();

        outboundOrderProductDetailDTO.setBinCode( entityBinCode( entity ) );
        outboundOrderProductDetailDTO.setOutboundOrderProductId( entityOutboundOrderProductId( entity ) );
        outboundOrderProductDetailDTO.setProductId( entityProductId( entity ) );
        outboundOrderProductDetailDTO.setBinId( entityBinId( entity ) );
        outboundOrderProductDetailDTO.setProductName( entityProductName( entity ) );
        outboundOrderProductDetailDTO.setId( entity.getId() );
        outboundOrderProductDetailDTO.setNum( entity.getNum() );

        return outboundOrderProductDetailDTO;
    }

    @Override
    public OutboundOrderProductDetail toEntity(OutboundOrderProductDetailDTO dto) {
        if ( dto == null ) {
            return null;
        }

        OutboundOrderProductDetail outboundOrderProductDetail = new OutboundOrderProductDetail();

        outboundOrderProductDetail.setProduct( outboundOrderProductDetailDTOToProduct( dto ) );
        outboundOrderProductDetail.setBin( outboundOrderProductDetailDTOToStorageBin( dto ) );
        outboundOrderProductDetail.setOutboundOrderProduct( outboundOrderProductDetailDTOToOutboundOrderProduct( dto ) );
        outboundOrderProductDetail.setId( dto.getId() );
        outboundOrderProductDetail.setNum( dto.getNum() );

        return outboundOrderProductDetail;
    }

    private String entityBinCode(OutboundOrderProductDetail outboundOrderProductDetail) {
        if ( outboundOrderProductDetail == null ) {
            return null;
        }
        StorageBin bin = outboundOrderProductDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String code = bin.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    private String entityOutboundOrderProductId(OutboundOrderProductDetail outboundOrderProductDetail) {
        if ( outboundOrderProductDetail == null ) {
            return null;
        }
        OutboundOrderProduct outboundOrderProduct = outboundOrderProductDetail.getOutboundOrderProduct();
        if ( outboundOrderProduct == null ) {
            return null;
        }
        String id = outboundOrderProduct.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityProductId(OutboundOrderProductDetail outboundOrderProductDetail) {
        if ( outboundOrderProductDetail == null ) {
            return null;
        }
        Product product = outboundOrderProductDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityBinId(OutboundOrderProductDetail outboundOrderProductDetail) {
        if ( outboundOrderProductDetail == null ) {
            return null;
        }
        StorageBin bin = outboundOrderProductDetail.getBin();
        if ( bin == null ) {
            return null;
        }
        String id = bin.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityProductName(OutboundOrderProductDetail outboundOrderProductDetail) {
        if ( outboundOrderProductDetail == null ) {
            return null;
        }
        Product product = outboundOrderProductDetail.getProduct();
        if ( product == null ) {
            return null;
        }
        String name = product.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    protected Product outboundOrderProductDetailDTOToProduct(OutboundOrderProductDetailDTO outboundOrderProductDetailDTO) {
        if ( outboundOrderProductDetailDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( outboundOrderProductDetailDTO.getProductName() );
        product.setId( outboundOrderProductDetailDTO.getProductId() );

        return product;
    }

    protected StorageBin outboundOrderProductDetailDTOToStorageBin(OutboundOrderProductDetailDTO outboundOrderProductDetailDTO) {
        if ( outboundOrderProductDetailDTO == null ) {
            return null;
        }

        StorageBin storageBin = new StorageBin();

        storageBin.setCode( outboundOrderProductDetailDTO.getBinCode() );
        storageBin.setId( outboundOrderProductDetailDTO.getBinId() );

        return storageBin;
    }

    protected OutboundOrderProduct outboundOrderProductDetailDTOToOutboundOrderProduct(OutboundOrderProductDetailDTO outboundOrderProductDetailDTO) {
        if ( outboundOrderProductDetailDTO == null ) {
            return null;
        }

        OutboundOrderProduct outboundOrderProduct = new OutboundOrderProduct();

        outboundOrderProduct.setId( outboundOrderProductDetailDTO.getOutboundOrderProductId() );

        return outboundOrderProduct;
    }
}
