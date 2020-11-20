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
public class DeliveryPlanMapperImpl implements DeliveryPlanMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public DeliveryPlanDTO toDTO(DeliveryPlan deliveryPlan) {
        if ( deliveryPlan == null ) {
            return null;
        }

        DeliveryPlanDTO deliveryPlanDTO = new DeliveryPlanDTO();

        String address = deliveryPlanSaleAddress( deliveryPlan );
        if ( address != null ) {
            deliveryPlanDTO.setAddress( address );
        }
        String id = deliveryPlanSaleId( deliveryPlan );
        if ( id != null ) {
            deliveryPlanDTO.setSaleId( id );
        }
        String orderNo = deliveryPlanSaleOrderNo( deliveryPlan );
        if ( orderNo != null ) {
            deliveryPlanDTO.setSaleNo( orderNo );
        }
        if ( deliveryPlan.getId() != null ) {
            deliveryPlanDTO.setId( deliveryPlan.getId() );
        }
        if ( deliveryPlan.getIsCompilePlan() != null ) {
            deliveryPlanDTO.setIsCompilePlan( deliveryPlan.getIsCompilePlan() );
        }
        if ( deliveryPlan.getDescription() != null ) {
            deliveryPlanDTO.setDescription( deliveryPlan.getDescription() );
        }
        if ( deliveryPlan.getName() != null ) {
            deliveryPlanDTO.setName( deliveryPlan.getName() );
        }
        if ( deliveryPlan.getDeliveryDate() != null ) {
            deliveryPlanDTO.setDeliveryDate( dateMapperUtil.localDateToString( deliveryPlan.getDeliveryDate() ) );
        }
        List<DeliveryPlanDetailsDTO> list = deliveryPlanDetailsListToDeliveryPlanDetailsDTOList( deliveryPlan.getDeliveryPlanDetailsList() );
        if ( list != null ) {
            deliveryPlanDTO.setDeliveryPlanDetailsList( list );
        }

        return deliveryPlanDTO;
    }

    @Override
    public DeliveryPlan toEntity(DeliveryPlanDTO deliveryPlanDTO) {
        if ( deliveryPlanDTO == null ) {
            return null;
        }

        DeliveryPlan deliveryPlan = new DeliveryPlan();

        deliveryPlan.setSale( deliveryPlanDTOToSale( deliveryPlanDTO ) );
        if ( deliveryPlanDTO.getIsCompilePlan() != null ) {
            deliveryPlan.setIsCompilePlan( deliveryPlanDTO.getIsCompilePlan() );
        }
        else {
            deliveryPlan.setIsCompilePlan( (byte) 0 );
        }
        if ( deliveryPlanDTO.getId() != null ) {
            deliveryPlan.setId( deliveryPlanDTO.getId() );
        }
        if ( deliveryPlanDTO.getDescription() != null ) {
            deliveryPlan.setDescription( deliveryPlanDTO.getDescription() );
        }
        if ( deliveryPlanDTO.getName() != null ) {
            deliveryPlan.setName( deliveryPlanDTO.getName() );
        }
        if ( deliveryPlanDTO.getDeliveryDate() != null ) {
            deliveryPlan.setDeliveryDate( dateMapperUtil.stringToLocalDate( deliveryPlanDTO.getDeliveryDate() ) );
        }
        List<DeliveryPlanDetails> list = deliveryPlanDetailsDTOListToDeliveryPlanDetailsList( deliveryPlanDTO.getDeliveryPlanDetailsList() );
        if ( list != null ) {
            deliveryPlan.setDeliveryPlanDetailsList( list );
        }

        return deliveryPlan;
    }

    private String deliveryPlanSaleAddress(DeliveryPlan deliveryPlan) {
        if ( deliveryPlan == null ) {
            return null;
        }
        Sale sale = deliveryPlan.getSale();
        if ( sale == null ) {
            return null;
        }
        String address = sale.getAddress();
        if ( address == null ) {
            return null;
        }
        return address;
    }

    private String deliveryPlanSaleId(DeliveryPlan deliveryPlan) {
        if ( deliveryPlan == null ) {
            return null;
        }
        Sale sale = deliveryPlan.getSale();
        if ( sale == null ) {
            return null;
        }
        String id = sale.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String deliveryPlanSaleOrderNo(DeliveryPlan deliveryPlan) {
        if ( deliveryPlan == null ) {
            return null;
        }
        Sale sale = deliveryPlan.getSale();
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

    protected DeliveryPlanDetailsDTO deliveryPlanDetailsToDeliveryPlanDetailsDTO(DeliveryPlanDetails deliveryPlanDetails) {
        if ( deliveryPlanDetails == null ) {
            return null;
        }

        DeliveryPlanDetailsDTO deliveryPlanDetailsDTO = new DeliveryPlanDetailsDTO();

        if ( deliveryPlanDetails.getId() != null ) {
            deliveryPlanDetailsDTO.setId( deliveryPlanDetails.getId() );
        }
        if ( deliveryPlanDetails.getProduct() != null ) {
            deliveryPlanDetailsDTO.setProduct( productToProductDTO( deliveryPlanDetails.getProduct() ) );
        }
        if ( deliveryPlanDetails.getNum() != null ) {
            deliveryPlanDetailsDTO.setNum( deliveryPlanDetails.getNum() );
        }

        return deliveryPlanDetailsDTO;
    }

    protected List<DeliveryPlanDetailsDTO> deliveryPlanDetailsListToDeliveryPlanDetailsDTOList(List<DeliveryPlanDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<DeliveryPlanDetailsDTO> list1 = new ArrayList<DeliveryPlanDetailsDTO>( list.size() );
        for ( DeliveryPlanDetails deliveryPlanDetails : list ) {
            list1.add( deliveryPlanDetailsToDeliveryPlanDetailsDTO( deliveryPlanDetails ) );
        }

        return list1;
    }

    protected Sale deliveryPlanDTOToSale(DeliveryPlanDTO deliveryPlanDTO) {
        if ( deliveryPlanDTO == null ) {
            return null;
        }

        Sale sale = new Sale();

        if ( deliveryPlanDTO.getSaleId() != null ) {
            sale.setId( deliveryPlanDTO.getSaleId() );
        }

        return sale;
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

    protected DeliveryPlanDetails deliveryPlanDetailsDTOToDeliveryPlanDetails(DeliveryPlanDetailsDTO deliveryPlanDetailsDTO) {
        if ( deliveryPlanDetailsDTO == null ) {
            return null;
        }

        DeliveryPlanDetails deliveryPlanDetails = new DeliveryPlanDetails();

        if ( deliveryPlanDetailsDTO.getId() != null ) {
            deliveryPlanDetails.setId( deliveryPlanDetailsDTO.getId() );
        }
        if ( deliveryPlanDetailsDTO.getProduct() != null ) {
            deliveryPlanDetails.setProduct( productDTOToProduct( deliveryPlanDetailsDTO.getProduct() ) );
        }
        if ( deliveryPlanDetailsDTO.getNum() != null ) {
            deliveryPlanDetails.setNum( deliveryPlanDetailsDTO.getNum() );
        }

        return deliveryPlanDetails;
    }

    protected List<DeliveryPlanDetails> deliveryPlanDetailsDTOListToDeliveryPlanDetailsList(List<DeliveryPlanDetailsDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<DeliveryPlanDetails> list1 = new ArrayList<DeliveryPlanDetails>( list.size() );
        for ( DeliveryPlanDetailsDTO deliveryPlanDetailsDTO : list ) {
            list1.add( deliveryPlanDetailsDTOToDeliveryPlanDetails( deliveryPlanDetailsDTO ) );
        }

        return list1;
    }
}
