package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkMenuService;

import java.util.Set;

/**
 * 菜单业务服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkMenuServiceImpl implements SdkMenuService {

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        // TODO: 通过 HTTP 调用查询权限
        return null;
    }

    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        // TODO: 通过 HTTP 调用查询权限
        return null;
    }
}
