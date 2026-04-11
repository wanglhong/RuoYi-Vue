package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 参数配置服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkConfigServiceImpl extends BaseHttpService implements SdkConfigService {

    private static final Logger log = LoggerFactory.getLogger(SdkConfigServiceImpl.class);

    /**
     * 根据键名查询参数配置信息
     *
     * @param configKey 参数键名
     * @return 参数键值
     */
    @Override
    public String selectConfigByKey(String configKey) {
        // TODO ---> [HTTP调用] 根据键名查询参数配置信息
        log.warn("TODO ---> [HTTP调用] 根据键名查询参数配置信息");
        return "";
    }

    /**
     * 获取验证码开关
     *
     * @return true开启，false关闭
     */
    @Override
    public boolean selectCaptchaEnabled() {
        // TODO ---> [HTTP调用] 获取验证码开关
        log.warn("TODO ---> [HTTP调用] 获取验证码开关");
        return false;
    }

}
