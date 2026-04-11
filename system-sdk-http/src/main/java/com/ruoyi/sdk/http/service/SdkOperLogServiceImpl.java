package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkOperLogService;
import com.ruoyi.system.domain.SysOperLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 操作日志服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkOperLogServiceImpl extends BaseHttpService implements SdkOperLogService {

    private static final Logger log = LoggerFactory.getLogger(SdkOperLogServiceImpl.class);

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        // TODO ---> [HTTP调用] 新增操作日志
        log.warn("TODO ---> [HTTP调用] 新增操作日志");
    }

}
