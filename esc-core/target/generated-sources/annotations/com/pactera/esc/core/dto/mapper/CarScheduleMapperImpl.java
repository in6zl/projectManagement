package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class CarScheduleMapperImpl implements CarScheduleMapper {

    @Override
    public CarSchedule toEntity(CarScheduleDTO d) {
        if ( d == null ) {
            return null;
        }

        CarSchedule carSchedule = new CarSchedule();

        carSchedule.setId( d.getId() );
        carSchedule.setStartTime( d.getStartTime() );
        carSchedule.setEndTime( d.getEndTime() );

        return carSchedule;
    }

    @Override
    public CarScheduleDTO toDTO(CarSchedule entity) {
        if ( entity == null ) {
            return null;
        }

        CarScheduleDTO carScheduleDTO = new CarScheduleDTO();

        carScheduleDTO.setOutProductOrderNo( entityOutProductOrderOrderNo( entity ) );
        carScheduleDTO.setCarNo( entityCarCarNo( entity ) );
        carScheduleDTO.setCarId( entityCarId( entity ) );
        carScheduleDTO.setOutProductOrderId( entityOutProductOrderId( entity ) );
        carScheduleDTO.setId( entity.getId() );
        carScheduleDTO.setStartTime( entity.getStartTime() );
        carScheduleDTO.setEndTime( entity.getEndTime() );

        return carScheduleDTO;
    }

    private String entityOutProductOrderOrderNo(CarSchedule carSchedule) {
        if ( carSchedule == null ) {
            return null;
        }
        OutProductOrder outProductOrder = carSchedule.getOutProductOrder();
        if ( outProductOrder == null ) {
            return null;
        }
        String orderNo = outProductOrder.getOrderNo();
        if ( orderNo == null ) {
            return null;
        }
        return orderNo;
    }

    private String entityCarCarNo(CarSchedule carSchedule) {
        if ( carSchedule == null ) {
            return null;
        }
        Car car = carSchedule.getCar();
        if ( car == null ) {
            return null;
        }
        String carNo = car.getCarNo();
        if ( carNo == null ) {
            return null;
        }
        return carNo;
    }

    private String entityCarId(CarSchedule carSchedule) {
        if ( carSchedule == null ) {
            return null;
        }
        Car car = carSchedule.getCar();
        if ( car == null ) {
            return null;
        }
        String id = car.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityOutProductOrderId(CarSchedule carSchedule) {
        if ( carSchedule == null ) {
            return null;
        }
        OutProductOrder outProductOrder = carSchedule.getOutProductOrder();
        if ( outProductOrder == null ) {
            return null;
        }
        String id = outProductOrder.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
