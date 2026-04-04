package com.ruoyi.sdk.service;

/**
 * 登录服务
 */
public interface SdkLoginService {

    /**
     * 获取验证码
     */
    void captchaImage();

    /**
     * 登录
     *
     * @param uuid     uuid
     * @param username 用户名
     * @param password 密码
     * @param code     验证码
     */
    void login(String uuid, String username, String password, String code);

    /**
     * 获取用户信息
     */
    void getInfo();

    /**
     * 获取路由信息
     */
    void getRouters();

    /**
     * 退出登录
     */
    void logout();

}
