package com.ruoyi.sdk.db.service;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sdk.service.SdkMenuService;
import com.ruoyi.system.service.ISysMenuService;

import java.util.Set;

/**
 * 菜单业务服务 DB 实现类
 *
 * @author ruoyi
 */
public class SdkMenuServiceImpl implements SdkMenuService {

    private ISysMenuService sysMenuService;

    public SdkMenuServiceImpl() {
    }

    private ISysMenuService getSysMenuService() {
        if (sysMenuService == null) {
            sysMenuService = SpringUtils.getBean(ISysMenuService.class);
        }
        return sysMenuService;
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        return getSysMenuService().selectMenuPermsByUserId(userId);
    }

    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        return getSysMenuService().selectMenuPermsByRoleId(roleId);
    }
}
