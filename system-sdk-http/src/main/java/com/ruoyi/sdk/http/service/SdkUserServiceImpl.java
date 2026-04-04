package com.ruoyi.sdk.http.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.sdk.service.SdkUserService;

import java.util.Date;

/**
 * 用户业务服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkUserServiceImpl implements SdkUserService {

    @Override
    public SysUser selectUserByUserName(String userName) {
        // TODO: 通过 HTTP 调用查询用户
        return null;
    }

    @Override
    public boolean checkUserNameUnique(SysUser user) {
        // TODO: 通过 HTTP 调用检查用户名是否唯一
        return false;
    }

    @Override
    public boolean registerUser(SysUser user) {
        // TODO: 通过 HTTP 调用注册用户信息
        return false;
    }

    @Override
    public void updateLoginInfo(Long userId, String loginIp, Date loginDate) {
        // TODO: 通过 HTTP 调用更新用户登录信息
    }
}
