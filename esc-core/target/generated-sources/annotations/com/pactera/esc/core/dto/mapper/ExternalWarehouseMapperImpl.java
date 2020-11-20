package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.util.DateMapperUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class ExternalWarehouseMapperImpl implements ExternalWarehouseMapper {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public ExternalWarehouse toEntity(ExternalWarehouseDTO d) {
        if ( d == null ) {
            return null;
        }

        ExternalWarehouse externalWarehouse = new ExternalWarehouse();

        externalWarehouse.setCreateTime( dateMapperUtil.stringToLocalDateTime( d.getCreateTime() ) );
        externalWarehouse.setId( d.getId() );
        externalWarehouse.setName( d.getName() );
        externalWarehouse.setAddress( d.getAddress() );
        externalWarehouse.setDescription( d.getDescription() );
        externalWarehouse.setPhone( d.getPhone() );
        externalWarehouse.setLongitude( d.getLongitude() );
        externalWarehouse.setLatitude( d.getLatitude() );
        externalWarehouse.setSuppliers( supplierDTOListToSupplierSet( d.getSuppliers() ) );

        return externalWarehouse;
    }

    @Override
    public ExternalWarehouseDTO toDTO(ExternalWarehouse entity) {
        if ( entity == null ) {
            return null;
        }

        ExternalWarehouseDTO externalWarehouseDTO = new ExternalWarehouseDTO();

        externalWarehouseDTO.setSuppliers( supplierSetToSupplierDTOList( entity.getSuppliers() ) );
        externalWarehouseDTO.setId( entity.getId() );
        externalWarehouseDTO.setName( entity.getName() );
        externalWarehouseDTO.setAddress( entity.getAddress() );
        externalWarehouseDTO.setDescription( entity.getDescription() );
        externalWarehouseDTO.setPhone( entity.getPhone() );
        externalWarehouseDTO.setLongitude( entity.getLongitude() );
        externalWarehouseDTO.setLatitude( entity.getLatitude() );
        externalWarehouseDTO.setCreateTime( dateMapperUtil.localDateTimeToString( entity.getCreateTime() ) );

        return externalWarehouseDTO;
    }

    @Override
    public void update(ExternalWarehouse oldWarehouse, ExternalWarehouseDTO newWarehouse) {
        if ( newWarehouse == null ) {
            return;
        }

        if ( newWarehouse.getCreateTime() != null ) {
            oldWarehouse.setCreateTime( dateMapperUtil.stringToLocalDateTime( newWarehouse.getCreateTime() ) );
        }
        if ( newWarehouse.getId() != null ) {
            oldWarehouse.setId( newWarehouse.getId() );
        }
        if ( newWarehouse.getName() != null ) {
            oldWarehouse.setName( newWarehouse.getName() );
        }
        if ( newWarehouse.getAddress() != null ) {
            oldWarehouse.setAddress( newWarehouse.getAddress() );
        }
        if ( newWarehouse.getDescription() != null ) {
            oldWarehouse.setDescription( newWarehouse.getDescription() );
        }
        if ( newWarehouse.getPhone() != null ) {
            oldWarehouse.setPhone( newWarehouse.getPhone() );
        }
        if ( newWarehouse.getLongitude() != null ) {
            oldWarehouse.setLongitude( newWarehouse.getLongitude() );
        }
        if ( newWarehouse.getLatitude() != null ) {
            oldWarehouse.setLatitude( newWarehouse.getLatitude() );
        }
        if ( oldWarehouse.getSuppliers() != null ) {
            Set<Supplier> set = supplierDTOListToSupplierSet( newWarehouse.getSuppliers() );
            if ( set != null ) {
                oldWarehouse.getSuppliers().clear();
                oldWarehouse.getSuppliers().addAll( set );
            }
        }
        else {
            Set<Supplier> set = supplierDTOListToSupplierSet( newWarehouse.getSuppliers() );
            if ( set != null ) {
                oldWarehouse.setSuppliers( set );
            }
        }
    }

    protected Supplier supplierDTOToSupplier(SupplierDTO supplierDTO) {
        if ( supplierDTO == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setCreateTime( dateMapperUtil.stringToLocalDateTime( supplierDTO.getCreateTime() ) );
        supplier.setId( supplierDTO.getId() );
        supplier.setCode( supplierDTO.getCode() );
        supplier.setName( supplierDTO.getName() );
        supplier.setAddress( supplierDTO.getAddress() );
        supplier.setDescription( supplierDTO.getDescription() );
        supplier.setPhone( supplierDTO.getPhone() );

        return supplier;
    }

    protected Set<Supplier> supplierDTOListToSupplierSet(List<SupplierDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Supplier> set = new HashSet<Supplier>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( SupplierDTO supplierDTO : list ) {
            set.add( supplierDTOToSupplier( supplierDTO ) );
        }

        return set;
    }

    protected SupplierDTO supplierToSupplierDTO(Supplier supplier) {
        if ( supplier == null ) {
            return null;
        }

        SupplierDTO supplierDTO = new SupplierDTO();

        supplierDTO.setId( supplier.getId() );
        supplierDTO.setName( supplier.getName() );
        supplierDTO.setCode( supplier.getCode() );
        supplierDTO.setAddress( supplier.getAddress() );
        supplierDTO.setDescription( supplier.getDescription() );
        supplierDTO.setPhone( supplier.getPhone() );
        supplierDTO.setCreateTime( dateMapperUtil.localDateTimeToString( supplier.getCreateTime() ) );

        return supplierDTO;
    }

    protected List<SupplierDTO> supplierSetToSupplierDTOList(Set<Supplier> set) {
        if ( set == null ) {
            return null;
        }

        List<SupplierDTO> list = new ArrayList<SupplierDTO>( set.size() );
        for ( Supplier supplier : set ) {
            list.add( supplierToSupplierDTO( supplier ) );
        }

        return list;
    }
}
