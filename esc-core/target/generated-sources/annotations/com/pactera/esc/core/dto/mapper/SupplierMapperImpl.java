package com.pactera.esc.core.dto.mapper;

import com.pactera.esc.core.util.DateMapperUtil;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class SupplierMapperImpl implements BaseMapper<Supplier, SupplierDTO> {

    @Autowired
    private DateMapperUtil dateMapperUtil;

    @Override
    public Supplier toEntity(SupplierDTO d) {
        if ( d == null ) {
            return null;
        }

        Supplier supplier = new Supplier();

        supplier.setCreateTime( dateMapperUtil.stringToLocalDateTime( d.getCreateTime() ) );
        supplier.setId( d.getId() );
        supplier.setCode( d.getCode() );
        supplier.setName( d.getName() );
        supplier.setAddress( d.getAddress() );
        supplier.setDescription( d.getDescription() );
        supplier.setPhone( d.getPhone() );

        return supplier;
    }

    @Override
    public SupplierDTO toDTO(Supplier e) {
        if ( e == null ) {
            return null;
        }

        SupplierDTO supplierDTO = new SupplierDTO();

        supplierDTO.setId( e.getId() );
        supplierDTO.setName( e.getName() );
        supplierDTO.setCode( e.getCode() );
        supplierDTO.setAddress( e.getAddress() );
        supplierDTO.setDescription( e.getDescription() );
        supplierDTO.setPhone( e.getPhone() );
        supplierDTO.setCreateTime( dateMapperUtil.localDateTimeToString( e.getCreateTime() ) );

        return supplierDTO;
    }
}
