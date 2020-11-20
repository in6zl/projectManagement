package com.pactera.esc.core.dto.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class InspectionRecordMapperImpl implements InspectionRecordMapper {

    @Override
    public InspectionRecord toEntity(InspectionRecordDTO d) {
        if ( d == null ) {
            return null;
        }

        InspectionRecord inspectionRecord = new InspectionRecord();

        if ( d.getCreator() != null ) {
            inspectionRecord.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            inspectionRecord.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            inspectionRecord.setCreateTime( LocalDateTime.ofInstant( d.getCreateTime().toInstant(), ZoneId.of( "UTC" ) ) );
        }
        if ( d.getUpdateTime() != null ) {
            inspectionRecord.setUpdateTime( LocalDateTime.parse( d.getUpdateTime() ) );
        }
        if ( d.getId() != null ) {
            inspectionRecord.setId( d.getId() );
        }
        if ( d.getPass() != null ) {
            inspectionRecord.setPass( d.getPass() );
        }
        if ( d.getExaminer() != null ) {
            inspectionRecord.setExaminer( d.getExaminer() );
        }
        if ( d.getUrl() != null ) {
            inspectionRecord.setUrl( d.getUrl() );
        }

        return inspectionRecord;
    }

    @Override
    public InspectionRecordDTO toDTO(InspectionRecord entity) {
        if ( entity == null ) {
            return null;
        }

        InspectionRecordDTO inspectionRecordDTO = new InspectionRecordDTO();

        String id = entityRecordId( entity );
        if ( id != null ) {
            inspectionRecordDTO.setRecordId( id );
        }
        String batch = entityRecordBatch( entity );
        if ( batch != null ) {
            inspectionRecordDTO.setRecordBatch( batch );
        }
        if ( entity.getId() != null ) {
            inspectionRecordDTO.setId( entity.getId() );
        }
        if ( entity.getPass() != null ) {
            inspectionRecordDTO.setPass( entity.getPass() );
        }
        if ( entity.getExaminer() != null ) {
            inspectionRecordDTO.setExaminer( entity.getExaminer() );
        }
        if ( entity.getUrl() != null ) {
            inspectionRecordDTO.setUrl( entity.getUrl() );
        }
        if ( entity.getCreator() != null ) {
            inspectionRecordDTO.setCreator( entity.getCreator() );
        }
        if ( entity.getOperator() != null ) {
            inspectionRecordDTO.setOperator( entity.getOperator() );
        }
        if ( entity.getCreateTime() != null ) {
            inspectionRecordDTO.setCreateTime( Date.from( entity.getCreateTime().toInstant( ZoneOffset.UTC ) ) );
        }
        if ( entity.getUpdateTime() != null ) {
            inspectionRecordDTO.setUpdateTime( DateTimeFormatter.ISO_LOCAL_DATE_TIME.format( entity.getUpdateTime() ) );
        }

        return inspectionRecordDTO;
    }

    private String entityRecordId(InspectionRecord inspectionRecord) {
        if ( inspectionRecord == null ) {
            return null;
        }
        ProductionRecord record = inspectionRecord.getRecord();
        if ( record == null ) {
            return null;
        }
        String id = record.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String entityRecordBatch(InspectionRecord inspectionRecord) {
        if ( inspectionRecord == null ) {
            return null;
        }
        ProductionRecord record = inspectionRecord.getRecord();
        if ( record == null ) {
            return null;
        }
        String batch = record.getBatch();
        if ( batch == null ) {
            return null;
        }
        return batch;
    }
}
