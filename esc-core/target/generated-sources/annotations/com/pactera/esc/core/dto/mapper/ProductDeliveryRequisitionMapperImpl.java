package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.util.DateMapperUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ProductDeliveryRequisitionMapperImpl implements ProductDeliveryRequisitionMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public ProductDeliveryRequisition toEntity(ProductDeliveryRequisitionDTO productDeliveryRequisitionDTO) {
        if ( productDeliveryRequisitionDTO == null ) {
            return null;
        }

        ProductDeliveryRequisition productDeliveryRequisition = new ProductDeliveryRequisition();

        productDeliveryRequisition.setSale( productDeliveryRequisitionDTOToSale( productDeliveryRequisitionDTO ) );
        productDeliveryRequisition.setId( productDeliveryRequisitionDTO.getId() );
        productDeliveryRequisition.setOutDate( dateMapperUtil.stringToLocalDateTime( productDeliveryRequisitionDTO.getOutDate() ) );
        productDeliveryRequisition.setAddress( productDeliveryRequisitionDTO.getAddress() );
        productDeliveryRequisition.setIsCompile( productDeliveryRequisitionDTO.getIsCompile() );
        productDeliveryRequisition.setOutOperator( productDeliveryRequisitionDTO.getOutOperator() );
        productDeliveryRequisition.setReceiver( productDeliveryRequisitionDTO.getReceiver() );
        productDeliveryRequisition.setReceiverPhone( productDeliveryRequisitionDTO.getReceiverPhone() );
        productDeliveryRequisition.setCarSchedule( productDeliveryRequisitionDTO.getCarSchedule() );
        productDeliveryRequisition.setDetailsList( productDeliveryRequisitionDetailsDTOListToProductDeliveryRequisitionDetailsList( productDeliveryRequisitionDTO.getDetailsList() ) );

        return productDeliveryRequisition;
    }

    @Override
    public ProductDeliveryRequisitionDTO toDTO(ProductDeliveryRequisition productDeliveryRequisition) {
        if ( productDeliveryRequisition == null ) {
            return null;
        }

        ProductDeliveryRequisitionDTO productDeliveryRequisitionDTO = new ProductDeliveryRequisitionDTO();

        productDeliveryRequisitionDTO.setSaleId( productDeliveryRequisitionSaleId( productDeliveryRequisition ) );
        productDeliveryRequisitionDTO.setSaleNo( productDeliveryRequisitionSaleOrderNo( productDeliveryRequisition ) );
        productDeliveryRequisitionDTO.setId( productDeliveryRequisition.getId() );
        productDeliveryRequisitionDTO.setOutDate( dateMapperUtil.localDateTimeToString( productDeliveryRequisition.getOutDate() ) );
        productDeliveryRequisitionDTO.setAddress( productDeliveryRequisition.getAddress() );
        productDeliveryRequisitionDTO.setIsCompile( productDeliveryRequisition.getIsCompile() );
        productDeliveryRequisitionDTO.setOutOperator( productDeliveryRequisition.getOutOperator() );
        productDeliveryRequisitionDTO.setReceiver( productDeliveryRequisition.getReceiver() );
        productDeliveryRequisitionDTO.setReceiverPhone( productDeliveryRequisition.getReceiverPhone() );
        productDeliveryRequisitionDTO.setDetailsList( productDeliveryRequisitionDetailsListToProductDeliveryRequisitionDetailsDTOList( productDeliveryRequisition.getDetailsList() ) );
        productDeliveryRequisitionDTO.setCarSchedule( productDeliveryRequisition.getCarSchedule() );

        return productDeliveryRequisitionDTO;
    }

    protected Sale productDeliveryRequisitionDTOToSale(ProductDeliveryRequisitionDTO productDeliveryRequisitionDTO) {
        if ( productDeliveryRequisitionDTO == null ) {
            return null;
        }

        Sale sale = new Sale();

        sale.setId( productDeliveryRequisitionDTO.getSaleId() );

        return sale;
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

    protected ProductDeliveryRequisitionDetails productDeliveryRequisitionDetailsDTOToProductDeliveryRequisitionDetails(ProductDeliveryRequisitionDetailsDTO productDeliveryRequisitionDetailsDTO) {
        if ( productDeliveryRequisitionDetailsDTO == null ) {
            return null;
        }

        ProductDeliveryRequisitionDetails productDeliveryRequisitionDetails = new ProductDeliveryRequisitionDetails();

        productDeliveryRequisitionDetails.setId( productDeliveryRequisitionDetailsDTO.getId() );
        productDeliveryRequisitionDetails.setProduct( productDTOToProduct( productDeliveryRequisitionDetailsDTO.getProduct() ) );
        if ( productDeliveryRequisitionDetailsDTO.getNum() != null ) {
            productDeliveryRequisitionDetails.setNum( productDeliveryRequisitionDetailsDTO.getNum() );
        }

        return productDeliveryRequisitionDetails;
    }

    protected List<ProductDeliveryRequisitionDetails> productDeliveryRequisitionDetailsDTOListToProductDeliveryRequisitionDetailsList(List<ProductDeliveryRequisitionDetailsDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDeliveryRequisitionDetails> list1 = new ArrayList<ProductDeliveryRequisitionDetails>( list.size() );
        for ( ProductDeliveryRequisitionDetailsDTO productDeliveryRequisitionDetailsDTO : list ) {
            list1.add( productDeliveryRequisitionDetailsDTOToProductDeliveryRequisitionDetails( productDeliveryRequisitionDetailsDTO ) );
        }

        return list1;
    }

    private String productDeliveryRequisitionSaleId(ProductDeliveryRequisition productDeliveryRequisition) {
        if ( productDeliveryRequisition == null ) {
            return null;
        }
        Sale sale = productDeliveryRequisition.getSale();
        if ( sale == null ) {
            return null;
        }
        String id = sale.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String productDeliveryRequisitionSaleOrderNo(ProductDeliveryRequisition productDeliveryRequisition) {
        if ( productDeliveryRequisition == null ) {
            return null;
        }
        Sale sale = productDeliveryRequisition.getSale();
        if ( sale == null ) {
            return null;
        }
        String orderNo = sale.getOrderNo();
        if ( orderNo == null ) {
            return null;
        }
        return orderNo;
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

    protected ProductDeliveryRequisitionDetailsDTO productDeliveryRequisitionDetailsToProductDeliveryRequisitionDetailsDTO(ProductDeliveryRequisitionDetails productDeliveryRequisitionDetails) {
        if ( productDeliveryRequisitionDetails == null ) {
            return null;
        }

        ProductDeliveryRequisitionDetailsDTO productDeliveryRequisitionDetailsDTO = new ProductDeliveryRequisitionDetailsDTO();

        productDeliveryRequisitionDetailsDTO.setId( productDeliveryRequisitionDetails.getId() );
        productDeliveryRequisitionDetailsDTO.setProduct( productToProductDTO( productDeliveryRequisitionDetails.getProduct() ) );
        productDeliveryRequisitionDetailsDTO.setNum( productDeliveryRequisitionDetails.getNum() );

        return productDeliveryRequisitionDetailsDTO;
    }

    protected List<ProductDeliveryRequisitionDetailsDTO> productDeliveryRequisitionDetailsListToProductDeliveryRequisitionDetailsDTOList(List<ProductDeliveryRequisitionDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<ProductDeliveryRequisitionDetailsDTO> list1 = new ArrayList<ProductDeliveryRequisitionDetailsDTO>( list.size() );
        for ( ProductDeliveryRequisitionDetails productDeliveryRequisitionDetails : list ) {
            list1.add( productDeliveryRequisitionDetailsToProductDeliveryRequisitionDetailsDTO( productDeliveryRequisitionDetails ) );
        }

        return list1;
    }
}
