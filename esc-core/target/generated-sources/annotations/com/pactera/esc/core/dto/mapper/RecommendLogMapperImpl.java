package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class RecommendLogMapperImpl implements BaseMapper<RecommendLog, RecommendLogDTO> {

    @Override
    public RecommendLog toEntity(RecommendLogDTO d) {
        if ( d == null ) {
            return null;
        }

        RecommendLog recommendLog = new RecommendLog();

        if ( d.getCreator() != null ) {
            recommendLog.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            recommendLog.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            recommendLog.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            recommendLog.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            recommendLog.setId( d.getId() );
        }
        if ( d.getStorehouseId() != null ) {
            recommendLog.setStorehouseId( d.getStorehouseId() );
        }
        if ( d.getText() != null ) {
            recommendLog.setText( d.getText() );
        }

        return recommendLog;
    }

    @Override
    public RecommendLogDTO toDTO(RecommendLog e) {
        if ( e == null ) {
            return null;
        }

        RecommendLogDTO recommendLogDTO = new RecommendLogDTO();

        if ( e.getId() != null ) {
            recommendLogDTO.setId( e.getId() );
        }
        if ( e.getStorehouseId() != null ) {
            recommendLogDTO.setStorehouseId( e.getStorehouseId() );
        }
        if ( e.getText() != null ) {
            recommendLogDTO.setText( e.getText() );
        }
        if ( e.getCreator() != null ) {
            recommendLogDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            recommendLogDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            recommendLogDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            recommendLogDTO.setUpdateTime( e.getUpdateTime() );
        }

        return recommendLogDTO;
    }
}
