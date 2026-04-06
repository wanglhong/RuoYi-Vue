package com.ruoyi.common.core.page;

import com.mybatisflex.core.paginate.Page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 带分页元数据的 List 实现
 *
 * @param <T>
 */
public class PageList<T> extends ArrayList<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private long total;
    /**
     * 当前页码
     */
    private long pageNum;
    /**
     * 每页大小
     */
    private long pageSize;

    public PageList() {
        super();
    }

    /**
     * 创建一个带分页元数据的 List
     *
     * @param pageData pageData
     * @param <T>      泛型
     * @return PageList
     */
    public static <T> PageList<T> of(Page<T> pageData) {
        return new PageList<T>(pageData.getRecords(), pageData.getTotalRow(), pageData.getPageNumber(), pageData.getPageSize());
    }

    /**
     * 创建一个带分页元数据的 List
     *
     * @param c
     * @param total
     * @param pageNum
     * @param pageSize
     * @param <T>
     * @return
     */
    public static <T> PageList<T> of(List<? extends T> c, long total, long pageNum, long pageSize) {
        return new PageList<>(c, total, pageNum, pageSize);
    }

    public PageList(List<? extends T> c, long total, long pageNum, long pageSize) {
        super(c);
        this.total = total;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

}
