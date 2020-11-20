package com.pactera.esc.core.dto.mapper;

import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class StorageBinRecommendMapperImpl implements BaseMapper<StorageBinRecommend, StorageBinRecommendDTO> {

    @Override
    public StorageBinRecommend toEntity(StorageBinRecommendDTO d) {
        if ( d == null ) {
            return null;
        }

        StorageBinRecommend storageBinRecommend = new StorageBinRecommend();

        if ( d.getCreator() != null ) {
            storageBinRecommend.setCreator( d.getCreator() );
        }
        if ( d.getOperator() != null ) {
            storageBinRecommend.setOperator( d.getOperator() );
        }
        if ( d.getCreateTime() != null ) {
            storageBinRecommend.setCreateTime( d.getCreateTime() );
        }
        if ( d.getUpdateTime() != null ) {
            storageBinRecommend.setUpdateTime( d.getUpdateTime() );
        }
        if ( d.getId() != null ) {
            storageBinRecommend.setId( d.getId() );
        }
        if ( d.getWarehouseId() != null ) {
            storageBinRecommend.setWarehouseId( d.getWarehouseId() );
        }
        if ( d.getTagOrder() != null ) {
            storageBinRecommend.setTagOrder( d.getTagOrder() );
        }
        if ( d.getEntranceOrder() != null ) {
            storageBinRecommend.setEntranceOrder( d.getEntranceOrder() );
        }
        if ( d.getExitOrder() != null ) {
            storageBinRecommend.setExitOrder( d.getExitOrder() );
        }
        if ( d.getDistanceOrder() != null ) {
            storageBinRecommend.setDistanceOrder( d.getDistanceOrder() );
        }
        if ( d.getEntranceValue() != null ) {
            storageBinRecommend.setEntranceValue( d.getEntranceValue() );
        }
        if ( d.getExitValue() != null ) {
            storageBinRecommend.setExitValue( d.getExitValue() );
        }
        if ( d.getDistanceValue() != null ) {
            storageBinRecommend.setDistanceValue( d.getDistanceValue() );
        }
        if ( d.getIsBinState() != null ) {
            storageBinRecommend.setIsBinState( d.getIsBinState() );
        }
        if ( d.getBinLockTime() != null ) {
            storageBinRecommend.setBinLockTime( d.getBinLockTime() );
        }

        return storageBinRecommend;
    }

    @Override
    public StorageBinRecommendDTO toDTO(StorageBinRecommend e) {
        if ( e == null ) {
            return null;
        }

        StorageBinRecommendDTO storageBinRecommendDTO = new StorageBinRecommendDTO();

        if ( e.getId() != null ) {
            storageBinRecommendDTO.setId( e.getId() );
        }
        if ( e.getWarehouseId() != null ) {
            storageBinRecommendDTO.setWarehouseId( e.getWarehouseId() );
        }
        if ( e.getTagOrder() != null ) {
            storageBinRecommendDTO.setTagOrder( e.getTagOrder() );
        }
        if ( e.getEntranceOrder() != null ) {
            storageBinRecommendDTO.setEntranceOrder( e.getEntranceOrder() );
        }
        if ( e.getExitOrder() != null ) {
            storageBinRecommendDTO.setExitOrder( e.getExitOrder() );
        }
        if ( e.getDistanceOrder() != null ) {
            storageBinRecommendDTO.setDistanceOrder( e.getDistanceOrder() );
        }
        if ( e.getEntranceValue() != null ) {
            storageBinRecommendDTO.setEntranceValue( e.getEntranceValue() );
        }
        if ( e.getExitValue() != null ) {
            storageBinRecommendDTO.setExitValue( e.getExitValue() );
        }
        if ( e.getDistanceValue() != null ) {
            storageBinRecommendDTO.setDistanceValue( e.getDistanceValue() );
        }
        if ( e.getIsBinState() != null ) {
            storageBinRecommendDTO.setIsBinState( e.getIsBinState() );
        }
        if ( e.getBinLockTime() != null ) {
            storageBinRecommendDTO.setBinLockTime( e.getBinLockTime() );
        }
        if ( e.getCreator() != null ) {
            storageBinRecommendDTO.setCreator( e.getCreator() );
        }
        if ( e.getOperator() != null ) {
            storageBinRecommendDTO.setOperator( e.getOperator() );
        }
        if ( e.getCreateTime() != null ) {
            storageBinRecommendDTO.setCreateTime( e.getCreateTime() );
        }
        if ( e.getUpdateTime() != null ) {
            storageBinRecommendDTO.setUpdateTime( e.getUpdateTime() );
        }

        return storageBinRecommendDTO;
    }
}
