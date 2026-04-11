package com.ruoyi.sdk.http.service;

import com.ruoyi.sdk.service.SdkRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * 角色业务服务 HTTP 实现类
 *
 * @author ruoyi
 */
public class SdkRoleServiceImpl extends BaseHttpService implements SdkRoleService {

    private static final Logger log = LoggerFactory.getLogger(SdkRoleServiceImpl.class);

    /**
     * 根据用户ID查询角色权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    @Override
    public Set<String> selectRolePermissionByUserId(Long userId) {
        // TODO ---> [HTTP调用] 根据用户ID查询角色权限
        log.warn("TODO ---> [HTTP调用] 根据用户ID查询角色权限");
        return Set.of();
    }

}
