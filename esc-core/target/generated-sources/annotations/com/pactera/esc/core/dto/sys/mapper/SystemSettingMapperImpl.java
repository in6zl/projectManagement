package com.pactera.esc.core.dto.sys.mapper;

import com.pactera.esc.core.domain.SystemSetting;
import com.pactera.esc.core.dto.sys.SystemSettingDTO;
import com.pactera.esc.core.vo.SystemSettingVO;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-08-31T10:08:53+0800",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class SystemSettingMapperImpl implements SystemSettingMapper {

    @Override
    public SystemSetting toEntity(SystemSettingDTO d) {
        if ( d == null ) {
            return null;
        }

        SystemSetting systemSetting = new SystemSetting();

        systemSetting.setId( d.getId() );
        systemSetting.setFrozenRequire( d.getFrozenRequire() );
        systemSetting.setFrozenTransfer( d.getFrozenTransfer() );
        systemSetting.setAddress( d.getAddress() );
        systemSetting.setLocation( d.getLocation() );

        return systemSetting;
    }

    @Override
    public SystemSettingDTO toDTO(SystemSetting e) {
        if ( e == null ) {
            return null;
        }

        SystemSettingDTO systemSettingDTO = new SystemSettingDTO();

        systemSettingDTO.setId( e.getId() );
        systemSettingDTO.setFrozenRequire( e.getFrozenRequire() );
        systemSettingDTO.setFrozenTransfer( e.getFrozenTransfer() );
        systemSettingDTO.setAddress( e.getAddress() );
        systemSettingDTO.setLocation( e.getLocation() );

        return systemSettingDTO;
    }

    @Override
    public SystemSettingVO toVO(SystemSetting systemSetting) {
        if ( systemSetting == null ) {
            return null;
        }

        SystemSettingVO systemSettingVO = new SystemSettingVO();

        systemSettingVO.setId( systemSetting.getId() );
        systemSettingVO.setFrozenRequire( systemSetting.getFrozenRequire() );
        systemSettingVO.setFrozenTransfer( systemSetting.getFrozenTransfer() );
        systemSettingVO.setAddress( systemSetting.getAddress() );
        systemSettingVO.setLocation( systemSetting.getLocation() );

        return systemSettingVO;
    }

    @Override
    public void update(SystemSetting db, SystemSettingDTO systemSetting) {
        if ( systemSetting == null ) {
            return;
        }

        if ( systemSetting.getId() != null ) {
            db.setId( systemSetting.getId() );
        }
        if ( systemSetting.getFrozenRequire() != null ) {
            db.setFrozenRequire( systemSetting.getFrozenRequire() );
        }
        if ( systemSetting.getFrozenTransfer() != null ) {
            db.setFrozenTransfer( systemSetting.getFrozenTransfer() );
        }
        if ( systemSetting.getAddress() != null ) {
            db.setAddress( systemSetting.getAddress() );
        }
        if ( systemSetting.getLocation() != null ) {
            db.setLocation( systemSetting.getLocation() );
        }
    }
}
