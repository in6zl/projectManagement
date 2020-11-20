package com.pactera.esc.core.dto.mapper;

import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public RegistrationDTO toDTO(Registration entity) {
        if ( entity == null ) {
            return null;
        }

        RegistrationDTO registrationDTO = new RegistrationDTO();

        List<InvoiceDeliveryInfoDTO> list = deliveryListDeliveryDTOList( entity.getDeliveryList() );
        if ( list != null ) {
            registrationDTO.setDeliveryDTOList( list );
        }
        if ( entity.getState() != null ) {
            registrationDTO.setStateName( registrationState( entity.getState() ) );
        }
        if ( entity.getId() != null ) {
            registrationDTO.setId( entity.getId() );
        }
        if ( entity.getCarNo() != null ) {
            registrationDTO.setCarNo( entity.getCarNo() );
        }
        if ( entity.getDriverName() != null ) {
            registrationDTO.setDriverName( entity.getDriverName() );
        }
        if ( entity.getDriverPhone() != null ) {
            registrationDTO.setDriverPhone( entity.getDriverPhone() );
        }
        if ( entity.getCarType() != null ) {
            registrationDTO.setCarType( entity.getCarType() );
        }
        if ( entity.getState() != null ) {
            registrationDTO.setState( entity.getState() );
        }
        if ( entity.getEnterTime() != null ) {
            registrationDTO.setEnterTime( entity.getEnterTime() );
        }
        if ( entity.getExitTime() != null ) {
            registrationDTO.setExitTime( entity.getExitTime() );
        }
        if ( entity.getIsDelete() != null ) {
            registrationDTO.setIsDelete( entity.getIsDelete() );
        }

        return registrationDTO;
    }

    @Override
    public Registration toEntity(RegistrationDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Registration registration = new Registration();

        List<InvoiceDeliveryInfo> list = entity( dto.getDeliveryDTOList() );
        if ( list != null ) {
            registration.setDeliveryList( list );
        }
        if ( dto.getId() != null ) {
            registration.setId( dto.getId() );
        }
        if ( dto.getCarNo() != null ) {
            registration.setCarNo( dto.getCarNo() );
        }
        if ( dto.getDriverName() != null ) {
            registration.setDriverName( dto.getDriverName() );
        }
        if ( dto.getDriverPhone() != null ) {
            registration.setDriverPhone( dto.getDriverPhone() );
        }
        if ( dto.getCarType() != null ) {
            registration.setCarType( dto.getCarType() );
        }
        if ( dto.getState() != null ) {
            registration.setState( dto.getState() );
        }
        if ( dto.getEnterTime() != null ) {
            registration.setEnterTime( dto.getEnterTime() );
        }
        if ( dto.getIsDelete() != null ) {
            registration.setIsDelete( dto.getIsDelete() );
        }
        if ( dto.getExitTime() != null ) {
            registration.setExitTime( dto.getExitTime() );
        }

        return registration;
    }
}
