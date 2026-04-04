package com.ruoyi.sdk.db.service;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sdk.service.SdkOperLogService;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.service.ISysOperLogService;

/**
 * 操作日志服务 DB 实现类
 *
 * @author ruoyi
 */
public class SdkOperLogServiceImpl implements SdkOperLogService {

    private ISysOperLogService sysOperLogService;

    public SdkOperLogServiceImpl() {
    }

    private ISysOperLogService getSysOperLogService() {
        if (sysOperLogService == null) {
            sysOperLogService = SpringUtils.getBean(ISysOperLogService.class);
        }
        return sysOperLogService;
    }

    @Override
    public void insertOperlog(SysOperLog operLog) {
        getSysOperLogService().insertOperlog(operLog);
    }
}
