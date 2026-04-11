package com.ruoyi.sdk.http.service;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.sdk.service.SdkUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * 用户业务服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkUserServiceImpl extends BaseHttpService implements SdkUserService {

    private static final Logger log = LoggerFactory.getLogger(SdkUserServiceImpl.class);

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByUserName(String userName) {
        // TODO ---> [HTTP调用] 通过用户名查询用户
        log.warn("TODO ---> [HTTP调用] 通过用户名查询用户");
        return null;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean checkUserNameUnique(SysUser user) {
        // TODO ---> [HTTP调用] 校验用户名称是否唯一
        log.warn("TODO ---> [HTTP调用] 校验用户名称是否唯一");
        return false;
    }

    /**
     * 注册用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public boolean registerUser(SysUser user) {
        // TODO ---> [HTTP调用] 注册用户信息
        log.warn("TODO ---> [HTTP调用] 注册用户信息");
        return false;
    }

    /**
     * 更新用户登录信息（IP和登录时间）
     *
     * @param userId    用户ID
     * @param loginIp   登录IP地址
     * @param loginDate 登录时间
     * @return 结果
     */
    @Override
    public void updateLoginInfo(Long userId, String loginIp, Date loginDate) {
        // TODO ---> [HTTP调用] 更新用户登录信息（IP和登录时间）
        log.warn("TODO ---> [HTTP调用] 更新用户登录信息（IP和登录时间）");
    }

}
