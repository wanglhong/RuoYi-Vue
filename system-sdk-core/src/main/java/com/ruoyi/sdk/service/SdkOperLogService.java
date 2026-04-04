package com.ruoyi.sdk.service;

import com.ruoyi.system.domain.SysOperLog;

/**
 * 操作日志 服务层
 *
 * @author ruoyi
 */
public interface SdkOperLogService {

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    void insertOperlog(SysOperLog operLog);

}
