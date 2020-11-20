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
public class OutProductOrderDetailsMapperImpl implements BaseMapper<OutProductOrderDetails, OutProductOrderDetailsDTO> {

    @Override
    public OutProductOrderDetails toEntity(OutProductOrderDetailsDTO d) {
        if ( d == null ) {
            return null;
        }

        OutProductOrderDetails outProductOrderDetails = new OutProductOrderDetails();

        if ( d.getId() != null ) {
            outProductOrderDetails.setId( d.getId() );
        }
        if ( d.getProduct() != null ) {
            outProductOrderDetails.setProduct( productDTOToProduct( d.getProduct() ) );
        }
        if ( d.getNum() != null ) {
            outProductOrderDetails.setNum( d.getNum() );
        }
        if ( d.getCreator() != null ) {
            outProductOrderDetails.setCreator( d.getCreator() );
        }
        if ( d.getCreateTime() != null ) {
            outProductOrderDetails.setCreateTime( d.getCreateTime() );
        }
        if ( d.getOperator() != null ) {
            outProductOrderDetails.setOperator( d.getOperator() );
        }
        if ( d.getUpdateTime() != null ) {
            outProductOrderDetails.setUpdateTime( d.getUpdateTime() );
        }

        return outProductOrderDetails;
    }

    @Override
    public OutProductOrderDetailsDTO toDTO(OutProductOrderDetails e) {
        if ( e == null ) {
            return null;
        }

        OutProductOrderDetailsDTO outProductOrderDetailsDTO = new OutProductOrderDetailsDTO();

        if ( e.getCreator() != null ) {
            outProductOrderDetailsDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            outProductOrderDetailsDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            outProductOrderDetailsDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            outProductOrderDetailsDTO.setUpdateTime( e.getUpdateTime() );
        }
        if ( e.getId() != null ) {
            outProductOrderDetailsDTO.setId( e.getId() );
        }
        if ( e.getProduct() != null ) {
            outProductOrderDetailsDTO.setProduct( productToProductDTO( e.getProduct() ) );
        }
        outProductOrderDetailsDTO.setNum( e.getNum() );

        return outProductOrderDetailsDTO;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        if ( productDTO.getId() != null ) {
            product.setId( productDTO.getId() );
        }
        if ( productDTO.getName() != null ) {
            product.setName( productDTO.getName() );
        }
        if ( productDTO.getSpec() != null ) {
            product.setSpec( productDTO.getSpec() );
        }
        if ( productDTO.getUnitName() != null ) {
            product.setUnitName( productDTO.getUnitName() );
        }
        if ( productDTO.getIsDelete() != null ) {
            product.setIsDelete( productDTO.getIsDelete() );
        }
        List<Tag> list = productDTO.getTags();
        if ( list != null ) {
            product.setTags( new ArrayList<Tag>( list ) );
        }

        return product;
    }

    protected ProductDTO productToProductDTO(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        if ( product.getId() != null ) {
            productDTO.setId( product.getId() );
        }
        if ( product.getName() != null ) {
            productDTO.setName( product.getName() );
        }
        if ( product.getSpec() != null ) {
            productDTO.setSpec( product.getSpec() );
        }
        if ( product.getUnitName() != null ) {
            productDTO.setUnitName( product.getUnitName() );
        }
        if ( product.getIsDelete() != null ) {
            productDTO.setIsDelete( product.getIsDelete() );
        }
        List<Tag> list = product.getTags();
        if ( list != null ) {
            productDTO.setTags( new ArrayList<Tag>( list ) );
        }

        return productDTO;
    }
}
