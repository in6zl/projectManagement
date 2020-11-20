package com.pactera.esc.controller.api;

import com.pactera.esc.core.common.JsonResult;
import com.pactera.esc.core.dto.sys.SystemSettingDTO;
import com.pactera.esc.core.vo.SystemSettingVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统参数设置
 * @author yayun
 */
@Api(tags = "系统参数设置")
public interface SystemSettingApi {

    @ApiOperation("获取系统参数配置")
    JsonResult<SystemSettingVO> get();

    @ApiOperation("更新系统参数")
    JsonResult<String> save(SystemSettingDTO systemSetting);
}
