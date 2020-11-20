package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.util.DateMapperUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public Invoice toEntity(InvoiceDTO d) {
        if ( d == null ) {
            return null;
        }

        Invoice invoice = new Invoice();

        invoice.setCreateTime( dateMapperUtil.stringToLocalDateTime( d.getCreateTime() ) );
        invoice.setId( d.getId() );
        invoice.setCode( d.getCode() );
        invoice.setSendDate( dateMapperUtil.stringToLocalDateTime( d.getSendDate() ) );
        invoice.setSendAddress( d.getSendAddress() );
        invoice.setSendType( d.getSendType() );
        invoice.setExpectArriveTime( dateMapperUtil.stringToLocalDateTime( d.getExpectArriveTime() ) );
        invoice.setFinishTime( d.getFinishTime() );
        invoice.setNote( d.getNote() );
        invoice.setCarNo( d.getCarNo() );
        invoice.setState( d.getState() );
        invoice.setDriverName( d.getDriverName() );
        invoice.setDriverPhone( d.getDriverPhone() );
        invoice.setInvoiceDeliveryInfos( invoiceDeliveryInfoDTOListToInvoiceDeliveryInfoList( d.getInvoiceDeliveryInfos() ) );
        invoice.setSupplier( d.getSupplier() );
        invoice.setInboundOrderMaterials( inboundOrderMaterialDTOListToInboundOrderMaterialList( d.getInboundOrderMaterials() ) );

        return invoice;
    }

    @Override
    public InvoiceDTO toDTO(Invoice invoice) {
        if ( invoice == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setInvoiceDeliveryInfos( invoiceMapperInvoiceDeliveryInfo( invoice.getInvoiceDeliveryInfos() ) );
        invoiceDTO.setStateName( invoiceMapperState( invoice.getState() ) );
        invoiceDTO.setId( invoice.getId() );
        invoiceDTO.setCode( invoice.getCode() );
        invoiceDTO.setSendDate( dateMapperUtil.localDateTimeToString( invoice.getSendDate() ) );
        invoiceDTO.setSendAddress( invoice.getSendAddress() );
        invoiceDTO.setSendType( invoice.getSendType() );
        invoiceDTO.setExpectArriveTime( dateMapperUtil.localDateTimeToString( invoice.getExpectArriveTime() ) );
        invoiceDTO.setNote( invoice.getNote() );
        invoiceDTO.setCarNo( invoice.getCarNo() );
        invoiceDTO.setState( invoice.getState() );
        invoiceDTO.setDriverName( invoice.getDriverName() );
        invoiceDTO.setDriverPhone( invoice.getDriverPhone() );
        invoiceDTO.setFinishTime( invoice.getFinishTime() );
        invoiceDTO.setSupplier( invoice.getSupplier() );
        invoiceDTO.setCreateTime( dateMapperUtil.localDateTimeToString( invoice.getCreateTime() ) );
        invoiceDTO.setInboundOrderMaterials( inboundOrderMaterialListToInboundOrderMaterialDTOList( invoice.getInboundOrderMaterials() ) );

        return invoiceDTO;
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

    protected InvoiceDeliveryInfo invoiceDeliveryInfoDTOToInvoiceDeliveryInfo(InvoiceDeliveryInfoDTO invoiceDeliveryInfoDTO) {
        if ( invoiceDeliveryInfoDTO == null ) {
            return null;
        }

        InvoiceDeliveryInfo invoiceDeliveryInfo = new InvoiceDeliveryInfo();

        invoiceDeliveryInfo.setId( invoiceDeliveryInfoDTO.getId() );
        invoiceDeliveryInfo.setInvoiceDeliveryInfoDetails( invoiceDeliveryInfoDetailsDTOListToInvoiceDeliveryInfoDetailsList( invoiceDeliveryInfoDTO.getInvoiceDeliveryInfoDetails() ) );
        invoiceDeliveryInfo.setWarehouse( warehouseDTOToWarehouse( invoiceDeliveryInfoDTO.getWarehouse() ) );
        invoiceDeliveryInfo.setRegistration( registrationDTOToRegistration( invoiceDeliveryInfoDTO.getRegistration() ) );
        invoiceDeliveryInfo.setStatus( invoiceDeliveryInfoDTO.getStatus() );
        invoiceDeliveryInfo.setEnterFaWaHouseTime( invoiceDeliveryInfoDTO.getEnterFaWaHouseTime() );
        invoiceDeliveryInfo.setOutFaWaHouseTime( invoiceDeliveryInfoDTO.getOutFaWaHouseTime() );
        invoiceDeliveryInfo.setRegistrationTime( invoiceDeliveryInfoDTO.getRegistrationTime() );
        invoiceDeliveryInfo.setGoodTakenInfo( invoiceDeliveryInfoDTO.getGoodTakenInfo() );
        invoiceDeliveryInfo.setNote( invoiceDeliveryInfoDTO.getNote() );
        Map<String, StorageBin> map = invoiceDeliveryInfoDTO.getExistStorageBinMap();
        if ( map != null ) {
            invoiceDeliveryInfo.setExistStorageBinMap( new HashMap<String, StorageBin>( map ) );
        }

        return invoiceDeliveryInfo;
    }

    protected List<InvoiceDeliveryInfo> invoiceDeliveryInfoDTOListToInvoiceDeliveryInfoList(List<InvoiceDeliveryInfoDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<InvoiceDeliveryInfo> list1 = new ArrayList<InvoiceDeliveryInfo>( list.size() );
        for ( InvoiceDeliveryInfoDTO invoiceDeliveryInfoDTO : list ) {
            list1.add( invoiceDeliveryInfoDTOToInvoiceDeliveryInfo( invoiceDeliveryInfoDTO ) );
        }

        return list1;
    }

    protected InboundOrderMaterial inboundOrderMaterialDTOToInboundOrderMaterial(InboundOrderMaterialDTO inboundOrderMaterialDTO) {
        if ( inboundOrderMaterialDTO == null ) {
            return null;
        }

        InboundOrderMaterial inboundOrderMaterial = new InboundOrderMaterial();

        inboundOrderMaterial.setId( inboundOrderMaterialDTO.getId() );
        inboundOrderMaterial.setCode( inboundOrderMaterialDTO.getCode() );
        inboundOrderMaterial.setInvoice( inboundOrderMaterialDTO.getInvoice() );
        inboundOrderMaterial.setWarehouse( warehouseDTOToWarehouse( inboundOrderMaterialDTO.getWarehouse() ) );
        inboundOrderMaterial.setInOperator( inboundOrderMaterialDTO.getInOperator() );
        inboundOrderMaterial.setDeliveryMan( inboundOrderMaterialDTO.getDeliveryMan() );
        inboundOrderMaterial.setInTime( inboundOrderMaterialDTO.getInTime() );
        inboundOrderMaterial.setIsFinished( inboundOrderMaterialDTO.getIsFinished() );
        List<InboundOrderMaterialDetail> list = inboundOrderMaterialDTO.getDetailList();
        if ( list != null ) {
            inboundOrderMaterial.setDetailList( new ArrayList<InboundOrderMaterialDetail>( list ) );
        }
        inboundOrderMaterial.setSearchStartTime( inboundOrderMaterialDTO.getSearchStartTime() );
        inboundOrderMaterial.setSearchEndTime( inboundOrderMaterialDTO.getSearchEndTime() );

        return inboundOrderMaterial;
    }

    protected List<InboundOrderMaterial> inboundOrderMaterialDTOListToInboundOrderMaterialList(List<InboundOrderMaterialDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<InboundOrderMaterial> list1 = new ArrayList<InboundOrderMaterial>( list.size() );
        for ( InboundOrderMaterialDTO inboundOrderMaterialDTO : list ) {
            list1.add( inboundOrderMaterialDTOToInboundOrderMaterial( inboundOrderMaterialDTO ) );
        }

        return list1;
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

    protected InboundOrderMaterialDTO inboundOrderMaterialToInboundOrderMaterialDTO(InboundOrderMaterial inboundOrderMaterial) {
        if ( inboundOrderMaterial == null ) {
            return null;
        }

        InboundOrderMaterialDTO inboundOrderMaterialDTO = new InboundOrderMaterialDTO();

        inboundOrderMaterialDTO.setId( inboundOrderMaterial.getId() );
        inboundOrderMaterialDTO.setCode( inboundOrderMaterial.getCode() );
        inboundOrderMaterialDTO.setInvoice( inboundOrderMaterial.getInvoice() );
        inboundOrderMaterialDTO.setWarehouse( warehouseToWarehouseDTO( inboundOrderMaterial.getWarehouse() ) );
        inboundOrderMaterialDTO.setInOperator( inboundOrderMaterial.getInOperator() );
        inboundOrderMaterialDTO.setDeliveryMan( inboundOrderMaterial.getDeliveryMan() );
        inboundOrderMaterialDTO.setInTime( inboundOrderMaterial.getInTime() );
        inboundOrderMaterialDTO.setIsFinished( inboundOrderMaterial.getIsFinished() );
        List<InboundOrderMaterialDetail> list = inboundOrderMaterial.getDetailList();
        if ( list != null ) {
            inboundOrderMaterialDTO.setDetailList( new ArrayList<InboundOrderMaterialDetail>( list ) );
        }
        inboundOrderMaterialDTO.setSearchStartTime( inboundOrderMaterial.getSearchStartTime() );
        inboundOrderMaterialDTO.setSearchEndTime( inboundOrderMaterial.getSearchEndTime() );

        return inboundOrderMaterialDTO;
    }

    protected List<InboundOrderMaterialDTO> inboundOrderMaterialListToInboundOrderMaterialDTOList(List<InboundOrderMaterial> list) {
        if ( list == null ) {
            return null;
        }

        List<InboundOrderMaterialDTO> list1 = new ArrayList<InboundOrderMaterialDTO>( list.size() );
        for ( InboundOrderMaterial inboundOrderMaterial : list ) {
            list1.add( inboundOrderMaterialToInboundOrderMaterialDTO( inboundOrderMaterial ) );
        }

        return list1;
    }
}
