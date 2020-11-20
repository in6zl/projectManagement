package com.pactera.esc.core.dto.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InvoiceDeliveryInfoMapperImpl implements InvoiceDeliveryInfoMapper {

    @Override
    public InvoiceDeliveryInfoDTO toDTO(InvoiceDeliveryInfo supplierSendOrder) {
        if ( supplierSendOrder == null ) {
            return null;
        }

        InvoiceDeliveryInfoDTO invoiceDeliveryInfoDTO = new InvoiceDeliveryInfoDTO();

        invoiceDeliveryInfoDTO.setInvoiceDeliveryInfoDetails( unitFormat( supplierSendOrder.getInvoiceDeliveryInfoDetails() ) );
        invoiceDeliveryInfoDTO.setCarNo( supplierSendOrderInvoiceCarNo( supplierSendOrder ) );
        invoiceDeliveryInfoDTO.setStatusName( invoiceDeliveryInfoStatus( supplierSendOrder.getStatus() ) );
        invoiceDeliveryInfoDTO.setSendType( supplierSendOrderInvoiceSendType( supplierSendOrder ) );
        invoiceDeliveryInfoDTO.setInvoiceId( supplierSendOrderInvoiceId( supplierSendOrder ) );
        invoiceDeliveryInfoDTO.setDriverPhone( supplierSendOrderInvoiceDriverPhone( supplierSendOrder ) );
        invoiceDeliveryInfoDTO.setInvoiceCode( supplierSendOrderInvoiceCode( supplierSendOrder ) );
        invoiceDeliveryInfoDTO.setId( supplierSendOrder.getId() );
        invoiceDeliveryInfoDTO.setWarehouse( warehouseToWarehouseDTO( supplierSendOrder.getWarehouse() ) );
        invoiceDeliveryInfoDTO.setRegistration( registrationToRegistrationDTO( supplierSendOrder.getRegistration() ) );
        invoiceDeliveryInfoDTO.setStatus( supplierSendOrder.getStatus() );
        invoiceDeliveryInfoDTO.setEnterFaWaHouseTime( supplierSendOrder.getEnterFaWaHouseTime() );
        invoiceDeliveryInfoDTO.setOutFaWaHouseTime( supplierSendOrder.getOutFaWaHouseTime() );
        invoiceDeliveryInfoDTO.setRegistrationTime( supplierSendOrder.getRegistrationTime() );
        invoiceDeliveryInfoDTO.setGoodTakenInfo( supplierSendOrder.getGoodTakenInfo() );
        invoiceDeliveryInfoDTO.setNote( supplierSendOrder.getNote() );
        Map<String, StorageBin> map = supplierSendOrder.getExistStorageBinMap();
        if ( map != null ) {
            invoiceDeliveryInfoDTO.setExistStorageBinMap( new HashMap<String, StorageBin>( map ) );
        }

        return invoiceDeliveryInfoDTO;
    }

    @Override
    public InvoiceDeliveryInfo toEntity(InvoiceDeliveryInfoDTO infoDTO) {
        if ( infoDTO == null ) {
            return null;
        }

        InvoiceDeliveryInfo invoiceDeliveryInfo = new InvoiceDeliveryInfo();

        invoiceDeliveryInfo.setInvoice( invoiceDeliveryInfoDTOToInvoice( infoDTO ) );
        invoiceDeliveryInfo.setId( infoDTO.getId() );
        invoiceDeliveryInfo.setInvoiceDeliveryInfoDetails( invoiceDeliveryInfoDetailsDTOListToInvoiceDeliveryInfoDetailsList( infoDTO.getInvoiceDeliveryInfoDetails() ) );
        invoiceDeliveryInfo.setWarehouse( warehouseDTOToWarehouse( infoDTO.getWarehouse() ) );
        invoiceDeliveryInfo.setRegistration( registrationDTOToRegistration( infoDTO.getRegistration() ) );
        invoiceDeliveryInfo.setStatus( infoDTO.getStatus() );
        invoiceDeliveryInfo.setEnterFaWaHouseTime( infoDTO.getEnterFaWaHouseTime() );
        invoiceDeliveryInfo.setOutFaWaHouseTime( infoDTO.getOutFaWaHouseTime() );
        invoiceDeliveryInfo.setRegistrationTime( infoDTO.getRegistrationTime() );
        invoiceDeliveryInfo.setGoodTakenInfo( infoDTO.getGoodTakenInfo() );
        invoiceDeliveryInfo.setNote( infoDTO.getNote() );
        Map<String, StorageBin> map = infoDTO.getExistStorageBinMap();
        if ( map != null ) {
            invoiceDeliveryInfo.setExistStorageBinMap( new HashMap<String, StorageBin>( map ) );
        }

        return invoiceDeliveryInfo;
    }

    private String supplierSendOrderInvoiceCarNo(InvoiceDeliveryInfo invoiceDeliveryInfo) {
        if ( invoiceDeliveryInfo == null ) {
            return null;
        }
        Invoice invoice = invoiceDeliveryInfo.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String carNo = invoice.getCarNo();
        if ( carNo == null ) {
            return null;
        }
        return carNo;
    }

    private Integer supplierSendOrderInvoiceSendType(InvoiceDeliveryInfo invoiceDeliveryInfo) {
        if ( invoiceDeliveryInfo == null ) {
            return null;
        }
        Invoice invoice = invoiceDeliveryInfo.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        Integer sendType = invoice.getSendType();
        if ( sendType == null ) {
            return null;
        }
        return sendType;
    }

    private String supplierSendOrderInvoiceId(InvoiceDeliveryInfo invoiceDeliveryInfo) {
        if ( invoiceDeliveryInfo == null ) {
            return null;
        }
        Invoice invoice = invoiceDeliveryInfo.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String id = invoice.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String supplierSendOrderInvoiceDriverPhone(InvoiceDeliveryInfo invoiceDeliveryInfo) {
        if ( invoiceDeliveryInfo == null ) {
            return null;
        }
        Invoice invoice = invoiceDeliveryInfo.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String driverPhone = invoice.getDriverPhone();
        if ( driverPhone == null ) {
            return null;
        }
        return driverPhone;
    }

    private String supplierSendOrderInvoiceCode(InvoiceDeliveryInfo invoiceDeliveryInfo) {
        if ( invoiceDeliveryInfo == null ) {
            return null;
        }
        Invoice invoice = invoiceDeliveryInfo.getInvoice();
        if ( invoice == null ) {
            return null;
        }
        String code = invoice.getCode();
        if ( code == null ) {
            return null;
        }
        return code;
    }

    protected WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        warehouseDTO.setId( warehouse.getId() );
        warehouseDTO.setName( warehouse.getName() );
        warehouseDTO.setArea( warehouse.getArea() );
        warehouseDTO.setAddress( warehouse.getAddress() );
        warehouseDTO.setLongitude( warehouse.getLongitude() );
        warehouseDTO.setLatitude( warehouse.getLatitude() );
        warehouseDTO.setType( warehouse.getType() );
        warehouseDTO.setIsFull( warehouse.getIsFull() );
        warehouseDTO.setIsOpen( warehouse.getIsOpen() );
        warehouseDTO.setCreator( warehouse.getCreator() );
        warehouseDTO.setOperator( warehouse.getOperator() );
        warehouseDTO.setCreateTime( warehouse.getCreateTime() );
        warehouseDTO.setUpdateTime( warehouse.getUpdateTime() );
        warehouseDTO.setPageable( warehouse.getPageable() );

        return warehouseDTO;
    }

    protected RegistrationDTO registrationToRegistrationDTO(Registration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationDTO registrationDTO = new RegistrationDTO();

        registrationDTO.setId( registration.getId() );
        registrationDTO.setCarNo( registration.getCarNo() );
        registrationDTO.setDriverName( registration.getDriverName() );
        registrationDTO.setDriverPhone( registration.getDriverPhone() );
        registrationDTO.setCarType( registration.getCarType() );
        registrationDTO.setState( registration.getState() );
        registrationDTO.setEnterTime( registration.getEnterTime() );
        registrationDTO.setExitTime( registration.getExitTime() );
        registrationDTO.setIsDelete( registration.getIsDelete() );

        return registrationDTO;
    }

    protected Invoice invoiceDeliveryInfoDTOToInvoice(InvoiceDeliveryInfoDTO invoiceDeliveryInfoDTO) {
        if ( invoiceDeliveryInfoDTO == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setId( invoiceDeliveryInfoDTO.getInvoiceId() );

        return invoice;
    }

    protected Material materialDTOToMaterial(MaterialDTO materialDTO) {
        if ( materialDTO == null ) {
            return null;
        }

        Material material = new Material();

        material.setId( materialDTO.getId() );
        material.setCode( materialDTO.getCode() );
        material.setName( materialDTO.getName() );
        material.setSpec( materialDTO.getSpec() );
        material.setUnitName( materialDTO.getUnitName() );
        material.setIsDelete( materialDTO.getIsDelete() );

        return material;
    }

    protected InvoiceDeliveryInfoDetails invoiceDeliveryInfoDetailsDTOToInvoiceDeliveryInfoDetails(InvoiceDeliveryInfoDetailsDTO invoiceDeliveryInfoDetailsDTO) {
        if ( invoiceDeliveryInfoDetailsDTO == null ) {
            return null;
        }

        InvoiceDeliveryInfoDetails invoiceDeliveryInfoDetails = new InvoiceDeliveryInfoDetails();

        invoiceDeliveryInfoDetails.setId( invoiceDeliveryInfoDetailsDTO.getId() );
        invoiceDeliveryInfoDetails.setMaterial( materialDTOToMaterial( invoiceDeliveryInfoDetailsDTO.getMaterial() ) );
        invoiceDeliveryInfoDetails.setNum( invoiceDeliveryInfoDetailsDTO.getNum() );
        invoiceDeliveryInfoDetails.setReceiveNum( invoiceDeliveryInfoDetailsDTO.getReceiveNum() );
        invoiceDeliveryInfoDetails.setStorageBinNum( invoiceDeliveryInfoDetailsDTO.getStorageBinNum() );
        List<StorageBin> list = invoiceDeliveryInfoDetailsDTO.getStorageBins();
        if ( list != null ) {
            invoiceDeliveryInfoDetails.setStorageBins( new ArrayList<StorageBin>( list ) );
        }
        List<StorageBin> list1 = invoiceDeliveryInfoDetailsDTO.getStorageBinsSelect();
        if ( list1 != null ) {
            invoiceDeliveryInfoDetails.setStorageBinsSelect( new ArrayList<StorageBin>( list1 ) );
        }

        return invoiceDeliveryInfoDetails;
    }

    protected List<InvoiceDeliveryInfoDetails> invoiceDeliveryInfoDetailsDTOListToInvoiceDeliveryInfoDetailsList(List<InvoiceDeliveryInfoDetailsDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<InvoiceDeliveryInfoDetails> list1 = new ArrayList<InvoiceDeliveryInfoDetails>( list.size() );
        for ( InvoiceDeliveryInfoDetailsDTO invoiceDeliveryInfoDetailsDTO : list ) {
            list1.add( invoiceDeliveryInfoDetailsDTOToInvoiceDeliveryInfoDetails( invoiceDeliveryInfoDetailsDTO ) );
        }

        return list1;
    }

    protected Warehouse warehouseDTOToWarehouse(WarehouseDTO warehouseDTO) {
        if ( warehouseDTO == null ) {
            return null;
        }

        Warehouse warehouse = new Warehouse();

        warehouse.setCreator( warehouseDTO.getCreator() );
        warehouse.setOperator( warehouseDTO.getOperator() );
        warehouse.setCreateTime( warehouseDTO.getCreateTime() );
        warehouse.setUpdateTime( warehouseDTO.getUpdateTime() );
        warehouse.setId( warehouseDTO.getId() );
        warehouse.setName( warehouseDTO.getName() );
        warehouse.setArea( warehouseDTO.getArea() );
        warehouse.setAddress( warehouseDTO.getAddress() );
        warehouse.setLongitude( warehouseDTO.getLongitude() );
        warehouse.setLatitude( warehouseDTO.getLatitude() );
        warehouse.setType( warehouseDTO.getType() );
        warehouse.setIsFull( warehouseDTO.getIsFull() );
        warehouse.setIsOpen( warehouseDTO.getIsOpen() );
        warehouse.setPageable( warehouseDTO.getPageable() );

        return warehouse;
    }

    protected Registration registrationDTOToRegistration(RegistrationDTO registrationDTO) {
        if ( registrationDTO == null ) {
            return null;
        }

        Registration registration = new Registration();

        registration.setId( registrationDTO.getId() );
        registration.setCarNo( registrationDTO.getCarNo() );
        registration.setDriverName( registrationDTO.getDriverName() );
        registration.setDriverPhone( registrationDTO.getDriverPhone() );
        registration.setCarType( registrationDTO.getCarType() );
        registration.setState( registrationDTO.getState() );
        registration.setEnterTime( registrationDTO.getEnterTime() );
        registration.setIsDelete( registrationDTO.getIsDelete() );
        registration.setExitTime( registrationDTO.getExitTime() );

        return registration;
    }
}
