package com.ruoyi.sdk.db.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sdk.service.SdkUserService;
import com.ruoyi.system.service.ISysUserService;

import java.util.Date;

/**
 * 用户业务服务 DB 实现类
 *
 * @author ruoyi
 */
public class SdkUserServiceImpl implements SdkUserService {

    private ISysUserService sysUserService;

    public SdkUserServiceImpl() {
    }

    private ISysUserService getSysUserService() {
        if (sysUserService == null) {
            sysUserService = SpringUtils.getBean(ISysUserService.class);
        }
        return sysUserService;
    }

    @Override
    public SysUser selectUserByUserName(String userName) {
        return getSysUserService().selectUserByUserName(userName);
    }

    @Override
    public boolean checkUserNameUnique(SysUser user) {
        return getSysUserService().checkUserNameUnique(user);
    }

    @Override
    public boolean registerUser(SysUser user) {
        return getSysUserService().registerUser(user);
    }

    @Override
    public void updateLoginInfo(Long userId, String loginIp, Date loginDate) {
        getSysUserService().updateLoginInfo(userId, loginIp, loginDate);
    }
}
