package com.ruoyi.sdk.service;

import java.util.Set;

/**
 * 菜单 业务层
 *
 * @author ruoyi
 */
public interface SdkMenuService {

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByUserId(Long userId);

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    Set<String> selectMenuPermsByRoleId(Long roleId);

}
