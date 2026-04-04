package com.ruoyi.sdk.db.service;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sdk.service.SdkLogininforService;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.service.ISysLogininforService;

/**
 * 系统登录日志服务 DB 实现类
 *
 * @author ruoyi
 */
public class SdkLogininforServiceImpl implements SdkLogininforService {

    private ISysLogininforService sysLogininforService;

    public SdkLogininforServiceImpl() {
    }

    private ISysLogininforService getSysLogininforService() {
        if (sysLogininforService == null) {
            sysLogininforService = SpringUtils.getBean(ISysLogininforService.class);
        }
        return sysLogininforService;
    }

    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        getSysLogininforService().insertLogininfor(logininfor);
    }
}
