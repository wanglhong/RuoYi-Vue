package com.ruoyi.common.utils;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.common.utils.sql.SqlUtil;

/**
 * 分页工具类（兼容 MyBatis-Flex）
 *
 * @author ruoyi
 */
public class PageUtils {
    protected static final ThreadLocal<PageDomain> LOCAL_PAGE_DOMAIN = new ThreadLocal<PageDomain>();

    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        LOCAL_PAGE_DOMAIN.set(pageDomain);
    }

    /**
     * 获取请求分页数据
     * <p>
     * 获取后会立即清理（第二次获取的时候就无法获取到，以达到只有startPage()后第一个查询能够分页的效果）
     *
     * @return PageDomain
     */
    public static PageDomain getPageDomain() {
        PageDomain pageDomain = LOCAL_PAGE_DOMAIN.get();
        clearPage();
        return pageDomain;
    }

    /**
     * 获取分页数据（不清理）
     *
     * @return PageDomain
     */
    public static PageDomain getPageDomainSafe() {
        return LOCAL_PAGE_DOMAIN.get();
    }

    /**
     * 检查是否有分页参数
     *
     * @return true 表示有分页参数
     */
    public static boolean hasPage() {
        PageDomain pageDomain = LOCAL_PAGE_DOMAIN.get();
        return pageDomain != null && pageDomain.getPageNum() != null && pageDomain.getPageSize() != null;
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage() {
        LOCAL_PAGE_DOMAIN.remove();
    }
}
