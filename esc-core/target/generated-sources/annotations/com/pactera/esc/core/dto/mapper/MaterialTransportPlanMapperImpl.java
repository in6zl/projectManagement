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
public class MaterialTransportPlanMapperImpl implements MaterialTransportPlanMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public MaterialTransportPlan toEntity(MaterialTransportPlanDTO d) {
        if ( d == null ) {
            return null;
        }

        MaterialTransportPlan materialTransportPlan = new MaterialTransportPlan();

        if ( d.getId() != null ) {
            materialTransportPlan.setId( d.getId() );
        }
        if ( d.getMaterial() != null ) {
            materialTransportPlan.setMaterial( materialDTOToMaterial( d.getMaterial() ) );
        }
        if ( d.getNum() != null ) {
            materialTransportPlan.setNum( d.getNum() );
        }
        if ( d.getFinishedNum() != null ) {
            materialTransportPlan.setFinishedNum( d.getFinishedNum() );
        }
        if ( d.getSupplier() != null ) {
            materialTransportPlan.setSupplier( supplierDTOToSupplier( d.getSupplier() ) );
        }
        if ( d.getState() != null ) {
            materialTransportPlan.setState( d.getState() );
        }
        if ( d.getRequireTime() != null ) {
            materialTransportPlan.setRequireTime( d.getRequireTime() );
        }
        if ( d.getSource() != null ) {
            materialTransportPlan.setSource( d.getSource() );
        }
        List<Invoice> list = d.getInvoices();
        if ( list != null ) {
            materialTransportPlan.setInvoices( new ArrayList<Invoice>( list ) );
        }

        return materialTransportPlan;
    }

    @Override
    public MaterialTransportPlanDTO toDTO(MaterialTransportPlan materialTransportPlan) {
        if ( materialTransportPlan == null ) {
            return null;
        }

        MaterialTransportPlanDTO materialTransportPlanDTO = new MaterialTransportPlanDTO();

        if ( materialTransportPlan.getWarehouse() != null ) {
            materialTransportPlanDTO.setFaWarehouse( warehouseToWarehouseDTO( materialTransportPlan.getWarehouse() ) );
        }
        if ( materialTransportPlan.getId() != null ) {
            materialTransportPlanDTO.setId( materialTransportPlan.getId() );
        }
        if ( materialTransportPlan.getSupplier() != null ) {
            materialTransportPlanDTO.setSupplier( supplierToSupplierDTO( materialTransportPlan.getSupplier() ) );
        }
        if ( materialTransportPlan.getMaterial() != null ) {
            materialTransportPlanDTO.setMaterial( materialToMaterialDTO( materialTransportPlan.getMaterial() ) );
        }
        if ( materialTransportPlan.getNum() != null ) {
            materialTransportPlanDTO.setNum( materialTransportPlan.getNum() );
        }
        if ( materialTransportPlan.getState() != null ) {
            materialTransportPlanDTO.setState( materialTransportPlan.getState() );
        }
        if ( materialTransportPlan.getRequireTime() != null ) {
            materialTransportPlanDTO.setRequireTime( materialTransportPlan.getRequireTime() );
        }
        if ( materialTransportPlan.getSource() != null ) {
            materialTransportPlanDTO.setSource( materialTransportPlan.getSource() );
        }
        if ( materialTransportPlan.getFinishedNum() != null ) {
            materialTransportPlanDTO.setFinishedNum( materialTransportPlan.getFinishedNum() );
        }
        List<Invoice> list = materialTransportPlan.getInvoices();
        if ( list != null ) {
            materialTransportPlanDTO.setInvoices( new ArrayList<Invoice>( list ) );
        }

        return materialTransportPlanDTO;
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
            supplier.setCreateTime( dateMapperUtil.stringToLocalDateTime( supplierDTO.getCreateTime() ) );
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

    protected WarehouseDTO warehouseToWarehouseDTO(Warehouse warehouse) {
        if ( warehouse == null ) {
            return null;
        }

        WarehouseDTO warehouseDTO = new WarehouseDTO();

        if ( warehouse.getId() != null ) {
            warehouseDTO.setId( warehouse.getId() );
        }
        if ( warehouse.getName() != null ) {
            warehouseDTO.setName( warehouse.getName() );
        }
        if ( warehouse.getArea() != null ) {
            warehouseDTO.setArea( warehouse.getArea() );
        }
        if ( warehouse.getAddress() != null ) {
            warehouseDTO.setAddress( warehouse.getAddress() );
        }
        if ( warehouse.getLongitude() != null ) {
            warehouseDTO.setLongitude( warehouse.getLongitude() );
        }
        if ( warehouse.getLatitude() != null ) {
            warehouseDTO.setLatitude( warehouse.getLatitude() );
        }
        if ( warehouse.getType() != null ) {
            warehouseDTO.setType( warehouse.getType() );
        }
        if ( warehouse.getIsFull() != null ) {
            warehouseDTO.setIsFull( warehouse.getIsFull() );
        }
        if ( warehouse.getIsOpen() != null ) {
            warehouseDTO.setIsOpen( warehouse.getIsOpen() );
        }
        if ( warehouse.getCreator() != null ) {
            warehouseDTO.setCreator( warehouse.getCreator() );
        }
        if ( warehouse.getOperator() != null ) {
            warehouseDTO.setOperator( warehouse.getOperator() );
        }
        if ( warehouse.getCreateTime() != null ) {
            warehouseDTO.setCreateTime( warehouse.getCreateTime() );
        }
        if ( warehouse.getUpdateTime() != null ) {
            warehouseDTO.setUpdateTime( warehouse.getUpdateTime() );
        }
        if ( warehouse.getPageable() != null ) {
            warehouseDTO.setPageable( warehouse.getPageable() );
        }

        return warehouseDTO;
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
            supplierDTO.setCreateTime( dateMapperUtil.localDateTimeToString( supplier.getCreateTime() ) );
        }

        return supplierDTO;
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
}
