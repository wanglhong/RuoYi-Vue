package com.ruoyi.sdk.db.service;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.sdk.service.SdkRoleService;
import com.ruoyi.system.service.ISysRoleService;

import java.util.Set;

/**
 * 角色业务服务 DB 实现类
 *
 * @author ruoyi
 */
public class SdkRoleServiceImpl implements SdkRoleService {

    private ISysRoleService sysRoleService;

    public SdkRoleServiceImpl() {
    }

    private ISysRoleService getSysRoleService() {
        if (sysRoleService == null) {
            sysRoleService = SpringUtils.getBean(ISysRoleService.class);
        }
        return sysRoleService;
    }

    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        return getSysRoleService().selectRolePermissionByUserId(userId);
    }
}
