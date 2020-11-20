package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:52+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class CarMapperImpl implements CarMapper {

    @Override
    public Car toEntity(CarDTO d) {
        if ( d == null ) {
            return null;
        }

        Car car = new Car();

        car.setId( d.getId() );
        car.setCarNo( d.getCarNo() );
        car.setDriverName( d.getDriverName() );
        car.setDriverPhone( d.getDriverPhone() );
        car.setType( d.getType() );
        car.setIsOpen( d.getIsOpen() );
        car.setInUse( d.getInUse() );

        return car;
    }

    @Override
    public CarDTO toDTO(Car entity) {
        if ( entity == null ) {
            return null;
        }

        CarDTO carDTO = new CarDTO();

        carDTO.setId( entity.getId() );
        carDTO.setCarNo( entity.getCarNo() );
        carDTO.setDriverName( entity.getDriverName() );
        carDTO.setDriverPhone( entity.getDriverPhone() );
        carDTO.setType( entity.getType() );
        carDTO.setIsOpen( entity.getIsOpen() );
        carDTO.setInUse( entity.getInUse() );

        return carDTO;
    }
}
