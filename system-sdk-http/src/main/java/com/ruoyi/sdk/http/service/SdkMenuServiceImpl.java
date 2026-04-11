package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 菜单业务服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkMenuServiceImpl extends BaseHttpService implements SdkMenuService {

    private static final Logger log = LoggerFactory.getLogger(SdkMenuServiceImpl.class);

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByUserId(Long userId) {
        // TODO ---> [HTTP调用] 根据用户ID查询权限
        log.warn("TODO ---> [HTTP调用] 根据用户ID查询权限");
        return Set.of();
    }

    /**
     * 根据角色ID查询权限
     *
     * @param roleId 角色ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectMenuPermsByRoleId(Long roleId) {
        // TODO ---> [HTTP调用] 根据角色ID查询权限
        log.warn("TODO ---> [HTTP调用] 根据角色ID查询权限");
        return Set.of();
    }

}
