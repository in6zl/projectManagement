package com.pactera.esc.sys.service;

import com.pactera.esc.core.dto.sys.SystemSettingDTO;
import com.pactera.esc.core.vo.SystemSettingVO;

public interface SystemSettingService {
    /**
     * 获取参数设置
     * @return
     */
    SystemSettingVO get();

    /**
     * 新增或修改
     * @param systemSetting
     */
    void save(SystemSettingDTO systemSetting);
}
