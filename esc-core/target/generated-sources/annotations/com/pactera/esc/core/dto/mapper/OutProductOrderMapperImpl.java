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
public class OutProductOrderMapperImpl implements OutProductOrderMapper {

    @Override
    public OutProductOrderDTO toDTO(OutProductOrder entity) {
        if ( entity == null ) {
            return null;
        }

        OutProductOrderDTO outProductOrderDTO = new OutProductOrderDTO();

        String orderNo = entitySaleOrderNo( entity );
        if ( orderNo != null ) {
            outProductOrderDTO.setSaleOrderNo( orderNo );
        }
        String id = entitySaleId( entity );
        if ( id != null ) {
            outProductOrderDTO.setSaleId( id );
        }
        String carNo = entityCarCarNo( entity );
        if ( carNo != null ) {
            outProductOrderDTO.setCarNo( carNo );
        }
        String driverName = entityCarDriverName( entity );
        if ( driverName != null ) {
            outProductOrderDTO.setDriverName( driverName );
        }
        String driverPhone = entityCarDriverPhone( entity );
        if ( driverPhone != null ) {
            outProductOrderDTO.setDriverPhone( driverPhone );
        }
        String id1 = entityCarId( entity );
        if ( id1 != null ) {
            outProductOrderDTO.setCarId( id1 );
        }
        if ( entity.getCreator() != null ) {
            outProductOrderDTO.setCreator( entity.getCreator() );
        }
        if ( entity.getOperator() != null ) {
            outProductOrderDTO.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            outProductOrderDTO.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getUpdateTime() != null ) {
            outProductOrderDTO.setUpdateTime( entity.getUpdateTime() );
        }
        if ( entity.getId() != null ) {
            outProductOrderDTO.setId( entity.getId() );
        }
        if ( entity.getOrderNo() != null ) {
            outProductOrderDTO.setOrderNo( entity.getOrderNo() );
        }
        if ( entity.getOutDate() != null ) {
            outProductOrderDTO.setOutDate( entity.getOutDate() );
        }
        if ( entity.getAddress() != null ) {
            outProductOrderDTO.setAddress( entity.getAddress() );
        }
        if ( entity.getIsCompile() != null ) {
            outProductOrderDTO.setIsCompile( entity.getIsCompile() );
        }
        if ( entity.getDescription() != null ) {
            outProductOrderDTO.setDescription( entity.getDescription() );
        }
        if ( entity.getOutOperator() != null ) {
            outProductOrderDTO.setOutOperator( entity.getOutOperator() );
        }
        if ( entity.getReceiver() != null ) {
            outProductOrderDTO.setReceiver( entity.getReceiver() );
        }
        if ( entity.getReceiverPhone() != null ) {
            outProductOrderDTO.setReceiverPhone( entity.getReceiverPhone() );
        }
        List<OutProductOrderDetailsDTO> list = outProductOrderDetailsListToOutProductOrderDetailsDTOList( entity.getOutProductOrderDetailsList() );
        if ( list != null ) {
            outProductOrderDTO.setOutProductOrderDetailsList( list );
        }

        return outProductOrderDTO;
    }

    @Override
    public OutProductOrder toEntity(OutProductOrderDTO entity) {
        if ( entity == null ) {
            return null;
        }

        OutProductOrder outProductOrder = new OutProductOrder();

        outProductOrder.setSale( outProductOrderDTOToSale( entity ) );
        outProductOrder.setCar( outProductOrderDTOToCar( entity ) );
        if ( entity.getCreator() != null ) {
            outProductOrder.setCreator( entity.getCreator() );
        }
        if ( entity.getOperator() != null ) {
            outProductOrder.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            outProductOrder.setCreateTime( entity.getCreateTime() );
        }
        if ( entity.getUpdateTime() != null ) {
            outProductOrder.setUpdateTime( entity.getUpdateTime() );
        }
        if ( entity.getId() != null ) {
            outProductOrder.setId( entity.getId() );
        }
        if ( entity.getOrderNo() != null ) {
            outProductOrder.setOrderNo( entity.getOrderNo() );
        }
        if ( entity.getOutDate() != null ) {
            outProductOrder.setOutDate( entity.getOutDate() );
        }
        if ( entity.getAddress() != null ) {
            outProductOrder.setAddress( entity.getAddress() );
        }
        if ( entity.getIsCompile() != null ) {
            outProductOrder.setIsCompile( entity.getIsCompile() );
        }
        if ( entity.getDescription() != null ) {
            outProductOrder.setDescription( entity.getDescription() );
        }
        if ( entity.getOutOperator() != null ) {
            outProductOrder.setOutOperator( entity.getOutOperator() );
        }
        if ( entity.getReceiver() != null ) {
            outProductOrder.setReceiver( entity.getReceiver() );
        }
        if ( entity.getReceiverPhone() != null ) {
            outProductOrder.setReceiverPhone( entity.getReceiverPhone() );
        }
        List<OutProductOrderDetails> list = outProductOrderDetailsDTOListToOutProductOrderDetailsList( entity.getOutProductOrderDetailsList() );
        if ( list != null ) {
            outProductOrder.setOutProductOrderDetailsList( list );
        }

        return outProductOrder;
    }

    private String entitySaleOrderNo(OutProductOrder outProductOrder) {
        if ( outProductOrder == null ) {
            return null;
        }
        Sale sale = outProductOrder.getSale();
        if ( sale == null ) {
            return null;
        }
        String orderNo = sale.getOrderNo();
        if ( orderNo == null ) {
            return null;
        }
        return orderNo;
    }

    private String entitySaleId(OutProductOrder outProductOrder) {
        if ( outProductOrder == null ) {
            return null;
        }
        Sale sale = outProductOrder.getSale();
        if ( sale == null ) {
            return null;
        }
        String id = sale.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityCarCarNo(OutProductOrder outProductOrder) {
        if ( outProductOrder == null ) {
            return null;
        }
        Car car = outProductOrder.getCar();
        if ( car == null ) {
            return null;
        }
        String carNo = car.getCarNo();
        if ( carNo == null ) {
            return null;
        }
        return carNo;
    }

    private String entityCarDriverName(OutProductOrder outProductOrder) {
        if ( outProductOrder == null ) {
            return null;
        }
        Car car = outProductOrder.getCar();
        if ( car == null ) {
            return null;
        }
        String driverName = car.getDriverName();
        if ( driverName == null ) {
            return null;
        }
        return driverName;
    }

    private String entityCarDriverPhone(OutProductOrder outProductOrder) {
        if ( outProductOrder == null ) {
            return null;
        }
        Car car = outProductOrder.getCar();
        if ( car == null ) {
            return null;
        }
        String driverPhone = car.getDriverPhone();
        if ( driverPhone == null ) {
            return null;
        }
        return driverPhone;
    }

    private String entityCarId(OutProductOrder outProductOrder) {
        if ( outProductOrder == null ) {
            return null;
        }
        Car car = outProductOrder.getCar();
        if ( car == null ) {
            return null;
        }
        String id = car.getId();
        if ( id == null ) {
            return null;
        }
        return id;
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

    protected OutProductOrderDetailsDTO outProductOrderDetailsToOutProductOrderDetailsDTO(OutProductOrderDetails outProductOrderDetails) {
        if ( outProductOrderDetails == null ) {
            return null;
        }

        OutProductOrderDetailsDTO outProductOrderDetailsDTO = new OutProductOrderDetailsDTO();

        if ( outProductOrderDetails.getCreator() != null ) {
            outProductOrderDetailsDTO.setCreator( outProductOrderDetails.getCreator() );
        }
        if ( outProductOrderDetails.getOperator() != null ) {
            outProductOrderDetailsDTO.setOperator( outProductOrderDetails.getOperator() );
        }
        if ( outProductOrderDetails.getCreateTime() != null ) {
            outProductOrderDetailsDTO.setCreateTime( outProductOrderDetails.getCreateTime() );
        }
        if ( outProductOrderDetails.getUpdateTime() != null ) {
            outProductOrderDetailsDTO.setUpdateTime( outProductOrderDetails.getUpdateTime() );
        }
        if ( outProductOrderDetails.getId() != null ) {
            outProductOrderDetailsDTO.setId( outProductOrderDetails.getId() );
        }
        if ( outProductOrderDetails.getProduct() != null ) {
            outProductOrderDetailsDTO.setProduct( productToProductDTO( outProductOrderDetails.getProduct() ) );
        }
        outProductOrderDetailsDTO.setNum( outProductOrderDetails.getNum() );

        return outProductOrderDetailsDTO;
    }

    protected List<OutProductOrderDetailsDTO> outProductOrderDetailsListToOutProductOrderDetailsDTOList(List<OutProductOrderDetails> list) {
        if ( list == null ) {
            return null;
        }

        List<OutProductOrderDetailsDTO> list1 = new ArrayList<OutProductOrderDetailsDTO>( list.size() );
        for ( OutProductOrderDetails outProductOrderDetails : list ) {
            list1.add( outProductOrderDetailsToOutProductOrderDetailsDTO( outProductOrderDetails ) );
        }

        return list1;
    }

    protected Sale outProductOrderDTOToSale(OutProductOrderDTO outProductOrderDTO) {
        if ( outProductOrderDTO == null ) {
            return null;
        }

        Sale sale = new Sale();

        if ( outProductOrderDTO.getSaleId() != null ) {
            sale.setId( outProductOrderDTO.getSaleId() );
        }
        if ( outProductOrderDTO.getSaleOrderNo() != null ) {
            sale.setOrderNo( outProductOrderDTO.getSaleOrderNo() );
        }

        return sale;
    }

    protected Car outProductOrderDTOToCar(OutProductOrderDTO outProductOrderDTO) {
        if ( outProductOrderDTO == null ) {
            return null;
        }

        Car car = new Car();

        if ( outProductOrderDTO.getCarNo() != null ) {
            car.setCarNo( outProductOrderDTO.getCarNo() );
        }
        if ( outProductOrderDTO.getCarId() != null ) {
            car.setId( outProductOrderDTO.getCarId() );
        }

        return car;
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

    protected OutProductOrderDetails outProductOrderDetailsDTOToOutProductOrderDetails(OutProductOrderDetailsDTO outProductOrderDetailsDTO) {
        if ( outProductOrderDetailsDTO == null ) {
            return null;
        }

        OutProductOrderDetails outProductOrderDetails = new OutProductOrderDetails();

        if ( outProductOrderDetailsDTO.getId() != null ) {
            outProductOrderDetails.setId( outProductOrderDetailsDTO.getId() );
        }
        if ( outProductOrderDetailsDTO.getProduct() != null ) {
            outProductOrderDetails.setProduct( productDTOToProduct( outProductOrderDetailsDTO.getProduct() ) );
        }
        if ( outProductOrderDetailsDTO.getNum() != null ) {
            outProductOrderDetails.setNum( outProductOrderDetailsDTO.getNum() );
        }
        if ( outProductOrderDetailsDTO.getCreator() != null ) {
            outProductOrderDetails.setCreator( outProductOrderDetailsDTO.getCreator() );
        }
        if ( outProductOrderDetailsDTO.getCreateTime() != null ) {
            outProductOrderDetails.setCreateTime( outProductOrderDetailsDTO.getCreateTime() );
        }
        if ( outProductOrderDetailsDTO.getOperator() != null ) {
            outProductOrderDetails.setOperator( outProductOrderDetailsDTO.getOperator() );
        }
        if ( outProductOrderDetailsDTO.getUpdateTime() != null ) {
            outProductOrderDetails.setUpdateTime( outProductOrderDetailsDTO.getUpdateTime() );
        }

        return outProductOrderDetails;
    }

    protected List<OutProductOrderDetails> outProductOrderDetailsDTOListToOutProductOrderDetailsList(List<OutProductOrderDetailsDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OutProductOrderDetails> list1 = new ArrayList<OutProductOrderDetails>( list.size() );
        for ( OutProductOrderDetailsDTO outProductOrderDetailsDTO : list ) {
            list1.add( outProductOrderDetailsDTOToOutProductOrderDetails( outProductOrderDetailsDTO ) );
        }

        return list1;
    }
}
