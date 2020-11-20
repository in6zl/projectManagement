package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InvoiceDeliveryInfoStatusLogMapperImpl implements BaseMapper<InvoiceDeliveryInfoStatusLog, InvoiceDeliveryInfoStatusLogDTO> {

    @Override
    public InvoiceDeliveryInfoStatusLog toEntity(InvoiceDeliveryInfoStatusLogDTO d) {
        if ( d == null ) {
            return null;
        }

        InvoiceDeliveryInfoStatusLog invoiceDeliveryInfoStatusLog = new InvoiceDeliveryInfoStatusLog();

        invoiceDeliveryInfoStatusLog.setId( d.getId() );
        invoiceDeliveryInfoStatusLog.setName( d.getName() );
        invoiceDeliveryInfoStatusLog.setStatus( d.getStatus() );
        invoiceDeliveryInfoStatusLog.setTime( d.getTime() );
        invoiceDeliveryInfoStatusLog.setCarNo( d.getCarNo() );
        invoiceDeliveryInfoStatusLog.setWarehouse( d.getWarehouse() );

        return invoiceDeliveryInfoStatusLog;
    }

    @Override
    public InvoiceDeliveryInfoStatusLogDTO toDTO(InvoiceDeliveryInfoStatusLog e) {
        if ( e == null ) {
            return null;
        }

        InvoiceDeliveryInfoStatusLogDTO invoiceDeliveryInfoStatusLogDTO = new InvoiceDeliveryInfoStatusLogDTO();

        invoiceDeliveryInfoStatusLogDTO.setId( e.getId() );
        invoiceDeliveryInfoStatusLogDTO.setName( e.getName() );
        invoiceDeliveryInfoStatusLogDTO.setStatus( e.getStatus() );
        invoiceDeliveryInfoStatusLogDTO.setTime( e.getTime() );
        invoiceDeliveryInfoStatusLogDTO.setCarNo( e.getCarNo() );
        invoiceDeliveryInfoStatusLogDTO.setWarehouse( e.getWarehouse() );

        return invoiceDeliveryInfoStatusLogDTO;
    }
}
