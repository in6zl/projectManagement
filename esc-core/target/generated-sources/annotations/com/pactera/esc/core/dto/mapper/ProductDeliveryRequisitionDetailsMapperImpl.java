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
public class ProductDeliveryRequisitionDetailsMapperImpl implements BaseMapper<ProductDeliveryRequisitionDetails, ProductDeliveryRequisitionDetailsDTO> {

    @Override
    public ProductDeliveryRequisitionDetails toEntity(ProductDeliveryRequisitionDetailsDTO d) {
        if ( d == null ) {
            return null;
        }

        ProductDeliveryRequisitionDetails productDeliveryRequisitionDetails = new ProductDeliveryRequisitionDetails();

        productDeliveryRequisitionDetails.setId( d.getId() );
        productDeliveryRequisitionDetails.setProduct( productDTOToProduct( d.getProduct() ) );
        if ( d.getNum() != null ) {
            productDeliveryRequisitionDetails.setNum( d.getNum() );
        }

        return productDeliveryRequisitionDetails;
    }

    @Override
    public ProductDeliveryRequisitionDetailsDTO toDTO(ProductDeliveryRequisitionDetails e) {
        if ( e == null ) {
            return null;
        }

        ProductDeliveryRequisitionDetailsDTO productDeliveryRequisitionDetailsDTO = new ProductDeliveryRequisitionDetailsDTO();

        productDeliveryRequisitionDetailsDTO.setId( e.getId() );
        productDeliveryRequisitionDetailsDTO.setProduct( productToProductDTO( e.getProduct() ) );
        productDeliveryRequisitionDetailsDTO.setNum( e.getNum() );

        return productDeliveryRequisitionDetailsDTO;
    }

    protected Product productDTOToProduct(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setSpec( productDTO.getSpec() );
        product.setUnitName( productDTO.getUnitName() );
        product.setIsDelete( productDTO.getIsDelete() );
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

        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setSpec( product.getSpec() );
        productDTO.setUnitName( product.getUnitName() );
        productDTO.setIsDelete( product.getIsDelete() );
        List<Tag> list = product.getTags();
        if ( list != null ) {
            productDTO.setTags( new ArrayList<Tag>( list ) );
        }

        return productDTO;
    }
}
