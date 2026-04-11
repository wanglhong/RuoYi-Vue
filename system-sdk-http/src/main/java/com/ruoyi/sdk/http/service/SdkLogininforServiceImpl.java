package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkLogininforService;
import com.ruoyi.system.domain.SysLogininfor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统登录日志服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkLogininforServiceImpl extends BaseHttpService implements SdkLogininforService {

    private static final Logger log = LoggerFactory.getLogger(SdkLogininforServiceImpl.class);

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        // TODO ---> [HTTP调用] 新增系统登录日志
        log.warn("TODO ---> [HTTP调用] 新增系统登录日志");
    }

}
