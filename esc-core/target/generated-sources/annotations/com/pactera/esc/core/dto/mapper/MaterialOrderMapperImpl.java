package com.pactera.esc.core.dto.mapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class MaterialOrderMapperImpl implements MaterialOrderMapper {

    @Override
    public MaterialOrder toEntity(MaterialOrderDTO d) {
        if ( d == null ) {
            return null;
        }

        MaterialOrder materialOrder = new MaterialOrder();

        if ( d.getCreator() != null ) {
            materialOrder.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            materialOrder.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            materialOrder.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            materialOrder.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            materialOrder.setId( d.getId() );
        }
        if ( d.getMaterial() != null ) {
            materialOrder.setMaterial( materialDTOToMaterial( d.getMaterial() ) );
        }
        if ( d.getUnit() != null ) {
            materialOrder.setUnit( d.getUnit() );
        }
        if ( d.getNum() != null ) {
            materialOrder.setNum( d.getNum() );
        }
        if ( d.getSupplier() != null ) {
            materialOrder.setSupplier( supplierDTOToSupplier( d.getSupplier() ) );
        }
        if ( d.getDeliveryNum() != null ) {
            materialOrder.setDeliveryNum( d.getDeliveryNum() );
        }
        if ( d.getDeliveryDate() != null ) {
            materialOrder.setDeliveryDate( d.getDeliveryDate() );
        }
        if ( d.getAmount() != null ) {
            materialOrder.setAmount( d.getAmount() );
        }
        if ( d.getDocumentDate() != null ) {
            materialOrder.setDocumentDate( d.getDocumentDate() );
        }
        if ( d.getOrderNo() != null ) {
            materialOrder.setOrderNo( d.getOrderNo() );
        }
        if ( d.getNote() != null ) {
            materialOrder.setNote( d.getNote() );
        }

        return materialOrder;
    }

    @Override
    public MaterialOrderDTO toDTO(MaterialOrder e) {
        if ( e == null ) {
            return null;
        }

        MaterialOrderDTO materialOrderDTO = new MaterialOrderDTO();

        if ( e.getId() != null ) {
            materialOrderDTO.setId( e.getId() );
        }
        if ( e.getMaterial() != null ) {
            materialOrderDTO.setMaterial( materialToMaterialDTO( e.getMaterial() ) );
        }
        if ( e.getUnit() != null ) {
            materialOrderDTO.setUnit( e.getUnit() );
        }
        if ( e.getNum() != null ) {
            materialOrderDTO.setNum( e.getNum() );
        }
        if ( e.getDeliveryDate() != null ) {
            materialOrderDTO.setDeliveryDate( e.getDeliveryDate() );
        }
        if ( e.getSupplier() != null ) {
            materialOrderDTO.setSupplier( supplierToSupplierDTO( e.getSupplier() ) );
        }
        if ( e.getDeliveryNum() != null ) {
            materialOrderDTO.setDeliveryNum( e.getDeliveryNum() );
        }
        if ( e.getAmount() != null ) {
            materialOrderDTO.setAmount( e.getAmount() );
        }
        if ( e.getDocumentDate() != null ) {
            materialOrderDTO.setDocumentDate( e.getDocumentDate() );
        }
        if ( e.getOrderNo() != null ) {
            materialOrderDTO.setOrderNo( e.getOrderNo() );
        }
        if ( e.getNote() != null ) {
            materialOrderDTO.setNote( e.getNote() );
        }
        if ( e.getCreator() != null ) {
            materialOrderDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            materialOrderDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            materialOrderDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            materialOrderDTO.setUpdateTime( e.getUpdateTime() );
        }

        return materialOrderDTO;
    }

    @Override
    public MaterialOrderVO toVo(MaterialOrder materialOrder) {
        if ( materialOrder == null ) {
            return null;
        }

        MaterialOrderVO materialOrderVO = new MaterialOrderVO();

        String name = materialOrderSupplierName( materialOrder );
        if ( name != null ) {
            materialOrderVO.setSupplier( name );
        }
        if ( materialOrder.getId() != null ) {
            materialOrderVO.setId( materialOrder.getId() );
        }
        if ( materialOrder.getOrderNo() != null ) {
            materialOrderVO.setOrderNo( materialOrder.getOrderNo() );
        }
        if ( materialOrder.getMaterial() != null ) {
            materialOrderVO.setMaterial( materialToMaterialDTO( materialOrder.getMaterial() ) );
        }
        if ( materialOrder.getUnit() != null ) {
            materialOrderVO.setUnit( materialOrder.getUnit() );
        }
        if ( materialOrder.getNum() != null ) {
            materialOrderVO.setNum( materialOrder.getNum() );
        }
        if ( materialOrder.getAmount() != null ) {
            materialOrderVO.setAmount( materialOrder.getAmount() );
        }
        if ( materialOrder.getDeliveryNum() != null ) {
            materialOrderVO.setDeliveryNum( materialOrder.getDeliveryNum() );
        }
        if ( materialOrder.getDeliveryDate() != null ) {
            materialOrderVO.setDeliveryDate( materialOrder.getDeliveryDate() );
        }
        if ( materialOrder.getDocumentDate() != null ) {
            materialOrderVO.setDocumentDate( materialOrder.getDocumentDate() );
        }

        return materialOrderVO;
    }

    protected Material materialDTOToMaterial(MaterialDTO materialDTO) {
        if ( materialDTO == null ) {
            return null;
        }

        Material material = new Material();

        if ( materialDTO.getId() != null ) {
            material.setId( materialDTO.getId() );
        }
        if ( materialDTO.getCode() != null ) {
            material.setCode( materialDTO.getCode() );
        }
        if ( materialDTO.getName() != null ) {
            material.setName( materialDTO.getName() );
        }
        if ( materialDTO.getSpec() != null ) {
            material.setSpec( materialDTO.getSpec() );
        }
        if ( materialDTO.getUnitName() != null ) {
            material.setUnitName( materialDTO.getUnitName() );
        }
        if ( materialDTO.getIsDelete() != null ) {
            material.setIsDelete( materialDTO.getIsDelete() );
        }

        return material;
    }

    protected Supplier supplierDTOToSupplier(SupplierDTO supplierDTO) {
        if ( supplierDTO == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        if ( supplierDTO.getCreateTime() != null ) {
            supplier.setCreateTime( LocalDateTime.parse( supplierDTO.getCreateTime() ) );
        }
        if ( supplierDTO.getId() != null ) {
            supplier.setId( supplierDTO.getId() );
        }
        if ( supplierDTO.getCode() != null ) {
            supplier.setCode( supplierDTO.getCode() );
        }
        if ( supplierDTO.getName() != null ) {
            supplier.setName( supplierDTO.getName() );
        }
        if ( supplierDTO.getAddress() != null ) {
            supplier.setAddress( supplierDTO.getAddress() );
        }
        if ( supplierDTO.getDescription() != null ) {
            supplier.setDescription( supplierDTO.getDescription() );
        }
        if ( supplierDTO.getPhone() != null ) {
            supplier.setPhone( supplierDTO.getPhone() );
        }

        return supplier;
    }

    protected MaterialDTO materialToMaterialDTO(Material material) {
        if ( material == null ) {
            return null;
        }

        MaterialDTO materialDTO = new MaterialDTO();

        if ( material.getId() != null ) {
            materialDTO.setId( material.getId() );
        }
        if ( material.getName() != null ) {
            materialDTO.setName( material.getName() );
        }
        if ( material.getCode() != null ) {
            materialDTO.setCode( material.getCode() );
        }
        if ( material.getSpec() != null ) {
            materialDTO.setSpec( material.getSpec() );
        }
        if ( material.getUnitName() != null ) {
            materialDTO.setUnitName( material.getUnitName() );
        }
        if ( material.getIsDelete() != null ) {
            materialDTO.setIsDelete( material.getIsDelete() );
        }

        return materialDTO;
    }

    protected SupplierDTO supplierToSupplierDTO(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDTO supplierDTO = new SupplierDTO();

        if ( supplier.getId() != null ) {
            supplierDTO.setId( supplier.getId() );
        }
        if ( supplier.getName() != null ) {
            supplierDTO.setName( supplier.getName() );
        }
        if ( supplier.getCode() != null ) {
            supplierDTO.setCode( supplier.getCode() );
        }
        if ( supplier.getAddress() != null ) {
            supplierDTO.setAddress( supplier.getAddress() );
        }
        if ( supplier.getDescription() != null ) {
            supplierDTO.setDescription( supplier.getDescription() );
        }
        if ( supplier.getPhone() != null ) {
            supplierDTO.setPhone( supplier.getPhone() );
        }
        if ( supplier.getCreateTime() != null ) {
            supplierDTO.setCreateTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( supplier.getCreateTime() ) );
        }

        return supplierDTO;
    }

    private String materialOrderSupplierName(MaterialOrder materialOrder) {
        if ( materialOrder == null ) {
            return null;
        }
        Supplier supplier = materialOrder.getSupplier();
        if ( supplier == null ) {
            return null;
        }
        String name = supplier.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }
}
