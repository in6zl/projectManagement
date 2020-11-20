package com.pactera.esc.sys.service.impl;

import com.pactera.esc.core.domain.SystemSetting;
import com.pactera.esc.core.dto.sys.SystemSettingDTO;
import com.pactera.esc.core.dto.sys.mapper.SystemSettingMapper;
import com.pactera.esc.core.vo.SystemSettingVO;
import com.pactera.esc.sys.service.SystemSettingService;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SystemSettingServiceImpl implements SystemSettingService {
    private final SystemSettingRepository systemSettingRepository;
    private final SystemSettingMapper systemSettingMapper;

    @Override
    public SystemSettingVO get() {
        return systemSettingMapper.toVO(getOrCreate(null));
    }

    private SystemSetting getOrCreate(SystemSettingDTO setting) {
        return systemSettingRepository.findOne((Specification<SystemSetting>) null)
            .orElse(systemSettingMapper.toEntity(setting));
    }

    @Override
    public void save(SystemSettingDTO systemSetting) {
        SystemSetting setting = getOrCreate(systemSetting);
        systemSettingMapper.update(setting, systemSetting);
        systemSettingRepository.save(setting);
    }
}
