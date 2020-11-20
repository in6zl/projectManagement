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
public class SaleMapperImpl implements BaseMapper<Sale, SaleDTO> {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public Sale toEntity(SaleDTO d) {
        if ( d == null ) {
            return null;
        }

        Sale sale = new Sale();

        if ( d.getId() != null ) {
            sale.setId( d.getId() );
        }
        if ( d.getOrderNo() != null ) {
            sale.setOrderNo( d.getOrderNo() );
        }
        if ( d.getOrderTime() != null ) {
            sale.setOrderTime( dateMapperUtil.stringToLocalDateTime( d.getOrderTime() ) );
        }
        if ( d.getSeller() != null ) {
            sale.setSeller( d.getSeller() );
        }
        if ( d.getSellerPhone() != null ) {
            sale.setSellerPhone( d.getSellerPhone() );
        }
        if ( d.getAmount() != null ) {
            sale.setAmount( d.getAmount() );
        }
        if ( d.getState() != null ) {
            sale.setState( d.getState() );
        }
        if ( d.getExpectDate() != null ) {
            sale.setExpectDate( dateMapperUtil.stringToLocalDateTime( d.getExpectDate() ) );
        }
        if ( d.getAddress() != null ) {
            sale.setAddress( d.getAddress() );
        }
        List<SaleDetails> list = saleDetailsDTOListToSaleDetailsList( d.getSaleDetailsList() );
        if ( list != null ) {
            sale.setSaleDetailsList( list );
        }

        return sale;
    }

    @Override
    public SaleDTO toDTO(Sale e) {
        if ( e == null ) {
            return null;
        }

        SaleDTO saleDTO = new SaleDTO();

        if ( e.getId() != null ) {
            saleDTO.setId( e.getId() );
        }
        if ( e.getOrderNo() != null ) {
            saleDTO.setOrderNo( e.getOrderNo() );
        }
        if ( e.getOrderTime() != null ) {
            saleDTO.setOrderTime( dateMapperUtil.localDateTimeToString( e.getOrderTime() ) );
        }
        if ( e.getSeller() != null ) {
            saleDTO.setSeller( e.getSeller() );
        }
        if ( e.getSellerPhone() != null ) {
            saleDTO.setSellerPhone( e.getSellerPhone() );
        }
        if ( e.getAmount() != null ) {
            saleDTO.setAmount( e.getAmount() );
        }
        if ( e.getState() != null ) {
            saleDTO.setState( e.getState() );
        }
        if ( e.getExpectDate() != null ) {
            saleDTO.setExpectDate( dateMapperUtil.localDateTimeToString( e.getExpectDate() ) );
        }
        if ( e.getAddress() != null ) {
            saleDTO.setAddress( e.getAddress() );
        }
        List<SaleDetailsDTO> list = saleDetailsListToSaleDetailsDTOList( e.getSaleDetailsList() );
        if ( list != null ) {
            saleDTO.setSaleDetailsList( list );
        }

        return saleDTO;
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

    protected SaleDetails saleDetailsDTOToSaleDetails(SaleDetailsDTO saleDetailsDTO) {
        if ( saleDetailsDTO == null ) {
            return null;
        }

        SaleDetails saleDetails = new SaleDetails();

        if ( saleDetailsDTO.getId() != null ) {
            saleDetails.setId( saleDetailsDTO.getId() );
        }
        if ( saleDetailsDTO.getProduct() != null ) {
            saleDetails.setProduct( productDTOToProduct( saleDetailsDTO.getProduct() ) );
        }
        saleDetails.setNum( saleDetailsDTO.getNum() );
        if ( saleDetailsDTO.getUnit() != null ) {
            saleDetails.setUnit( saleDetailsDTO.getUnit() );
        }
        saleDetails.setDeliveryNum( saleDetailsDTO.getDeliveryNum() );
        if ( saleDetailsDTO.getIsCompileProduct() != null ) {
            saleDetails.setIsCompileProduct( saleDetailsDTO.getIsCompileProduct() );
        }

        return saleDetails;
    }

    protected List<SaleDetails> saleDetailsDTOListToSaleDetailsList(List<SaleDetailsDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<SaleDetails> list1 = new ArrayList<SaleDetails>( list.size() );
        for ( SaleDetailsDTO saleDetailsDTO : list ) {
            list1.add( saleDetailsDTOToSaleDetails( saleDetailsDTO ) );
        }

        return list1;
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

    protected SaleDetailsDTO saleDetailsToSaleDetailsDTO(SaleDetails saleDetails) {
        if ( saleDetails == null ) {
            return null;
        }

        SaleDetailsDTO saleDetailsDTO = new SaleDetailsDTO();

        if ( saleDetails.getId() != null ) {
            saleDetailsDTO.setId( saleDetails.getId() );
        }
        if ( saleDetails.getProduct() != null ) {
            saleDetailsDTO.setProduct( productToProductDTO( saleDetails.getProduct() ) );
        }
        saleDetailsDTO.setNum( saleDetails.getNum() );
        if ( saleDetails.getUnit() != null ) {
            saleDetailsDTO.setUnit( saleDetails.getUnit() );
        }
        saleDetailsDTO.setDeliveryNum( saleDetails.getDeliveryNum() );
        if ( saleDetails.getIsCompileProduct() != null ) {
            saleDetailsDTO.setIsCompileProduct( saleDetails.getIsCompileProduct() );
        }

        return saleDetailsDTO;
    }

    protected List<SaleDetailsDTO> saleDetailsListToSaleDetailsDTOList(List<SaleDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<SaleDetailsDTO> list1 = new ArrayList<SaleDetailsDTO>( list.size() );
        for ( SaleDetails saleDetails : list ) {
            list1.add( saleDetailsToSaleDetailsDTO( saleDetails ) );
        }

        return list1;
    }
}
