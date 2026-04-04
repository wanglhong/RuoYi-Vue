package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkConfigService;

/**
 * 参数配置服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkConfigServiceImpl implements SdkConfigService {

    @Override
    public String selectConfigByKey(String configKey) {
        // TODO: 通过 HTTP 调用查询参数配置信息
        return null;
    }

    @Override
    public boolean selectCaptchaEnabled() {
        // TODO: 通过 HTTP 调用获取验证码开关
        return false;
    }
}
