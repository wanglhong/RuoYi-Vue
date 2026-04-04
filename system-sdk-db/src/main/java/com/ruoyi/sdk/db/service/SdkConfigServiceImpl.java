package com.ruoyi.sdk.db.service;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sdk.service.SdkConfigService;
import com.ruoyi.system.service.ISysConfigService;

/**
 * 参数配置服务 DB 实现类
 *
 * @author ruoyi
 */
public class SdkConfigServiceImpl implements SdkConfigService {

    private ISysConfigService sysConfigService;

    public SdkConfigServiceImpl() {
    }

    private ISysConfigService getSysConfigService() {
        if (sysConfigService == null) {
            sysConfigService = SpringUtils.getBean(ISysConfigService.class);
        }
        return sysConfigService;
    }

    @Override
    public String selectConfigByKey(String configKey) {
        return getSysConfigService().selectConfigByKey(configKey);
    }

    @Override
    public boolean selectCaptchaEnabled() {
        return getSysConfigService().selectCaptchaEnabled();
    }

}
