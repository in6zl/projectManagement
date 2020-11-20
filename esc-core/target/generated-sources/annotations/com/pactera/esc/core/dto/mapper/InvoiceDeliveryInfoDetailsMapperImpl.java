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
public class InvoiceDeliveryInfoDetailsMapperImpl implements InvoiceDeliveryInfoDetailsMapper {

    @Override
    public InvoiceDeliveryInfoDetails toEntity(InvoiceDeliveryInfoDetailsDTO d) {
        if ( d == null ) {
            return null;
        }

        InvoiceDeliveryInfoDetails invoiceDeliveryInfoDetails = new InvoiceDeliveryInfoDetails();

        invoiceDeliveryInfoDetails.setId( d.getId() );
        invoiceDeliveryInfoDetails.setMaterial( materialDTOToMaterial( d.getMaterial() ) );
        invoiceDeliveryInfoDetails.setNum( d.getNum() );
        invoiceDeliveryInfoDetails.setReceiveNum( d.getReceiveNum() );
        invoiceDeliveryInfoDetails.setStorageBinNum( d.getStorageBinNum() );
        List<StorageBin> list = d.getStorageBins();
        if ( list != null ) {
            invoiceDeliveryInfoDetails.setStorageBins( new ArrayList<StorageBin>( list ) );
        }
        List<StorageBin> list1 = d.getStorageBinsSelect();
        if ( list1 != null ) {
            invoiceDeliveryInfoDetails.setStorageBinsSelect( new ArrayList<StorageBin>( list1 ) );
        }

        return invoiceDeliveryInfoDetails;
    }

    @Override
    public InvoiceDeliveryInfoDetailsDTO toDTO(InvoiceDeliveryInfoDetails invoiceDeliveryInfoDetails) {
        if ( invoiceDeliveryInfoDetails == null ) {
            return null;
        }

        InvoiceDeliveryInfoDetailsDTO invoiceDeliveryInfoDetailsDTO = new InvoiceDeliveryInfoDetailsDTO();

        invoiceDeliveryInfoDetailsDTO.setMaterial( materialToMaterialDTO( invoiceDeliveryInfoDetails.getMaterial() ) );
        invoiceDeliveryInfoDetailsDTO.setId( invoiceDeliveryInfoDetails.getId() );
        invoiceDeliveryInfoDetailsDTO.setNum( invoiceDeliveryInfoDetails.getNum() );
        invoiceDeliveryInfoDetailsDTO.setReceiveNum( invoiceDeliveryInfoDetails.getReceiveNum() );
        invoiceDeliveryInfoDetailsDTO.setStorageBinNum( invoiceDeliveryInfoDetails.getStorageBinNum() );
        List<StorageBin> list = invoiceDeliveryInfoDetails.getStorageBins();
        if ( list != null ) {
            invoiceDeliveryInfoDetailsDTO.setStorageBins( new ArrayList<StorageBin>( list ) );
        }
        List<StorageBin> list1 = invoiceDeliveryInfoDetails.getStorageBinsSelect();
        if ( list1 != null ) {
            invoiceDeliveryInfoDetailsDTO.setStorageBinsSelect( new ArrayList<StorageBin>( list1 ) );
        }

        return invoiceDeliveryInfoDetailsDTO;
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

    protected MaterialDTO materialToMaterialDTO(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialDTO materialDTO = new MaterialDTO();

        materialDTO.setUnitName( material.getUnitName() );
        materialDTO.setId( material.getId() );
        materialDTO.setName( material.getName() );
        materialDTO.setCode( material.getCode() );
        materialDTO.setSpec( material.getSpec() );
        materialDTO.setIsDelete( material.getIsDelete() );

        return materialDTO;
    }
}
