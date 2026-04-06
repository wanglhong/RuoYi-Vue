package com.ruoyi.common.core.mapper;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.exception.FlexAssert;
import com.mybatisflex.core.field.FieldQueryBuilder;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.provider.EntitySqlProvider;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.Row;
import com.mybatisflex.core.util.MapperUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.PageList;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.MyMapperUtil;
import com.ruoyi.common.utils.PageUtils;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface MyBaseMapper<T> extends BaseMapper<T> {

    /**
     * 根据 Map 来构建查询条件，查询多条数据。
     *
     * @param whereConditions 条件
     * @return 数据列表
     */
    @Override
    default @Nullable List<T> selectListByMap(@NonNull Map<String, Object> whereConditions) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListByMap(whereConditions);
        }
        return pageSelect(whereConditions, pageDomain);
    }

    private PageList<T> pageSelect(Map<String, Object> whereConditions, PageDomain pageDomain) {
        Page<T> page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        FlexAssert.notEmpty(whereConditions, "whereConditions");
        QueryWrapper queryWrapper = QueryWrapper.create().where(whereConditions);
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, null, false);
        return PageList.of(page);
    }

    private PageList<T> pageSelect(QueryCondition whereConditions, PageDomain pageDomain) {
        Page<T> page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        FlexAssert.notNull(whereConditions, "whereConditions");
        QueryWrapper queryWrapper = QueryWrapper.create().where(whereConditions);
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, null, false);
        return PageList.of(page);
    }

    private PageList<T> pageSelect(QueryCondition whereConditions, PageDomain pageDomain, Consumer<FieldQueryBuilder<T>>... consumers) {
        Page<T> page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        FlexAssert.notNull(whereConditions, "whereConditions");
        QueryWrapper queryWrapper = QueryWrapper.create().where(whereConditions);
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, null, false, consumers);
        return PageList.of(page);
    }

    private <R> PageList<R> pageSelect(QueryWrapper queryWrapper, PageDomain pageDomain, Class<R> asType) {
        Page<R> page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, asType, false);
        return PageList.of(page);
    }

    private PageList<T> pageSelect(QueryWrapper queryWrapper, PageDomain pageDomain, Consumer<FieldQueryBuilder<T>>... consumers) {
        Page<T> page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, null, false, consumers);
        return PageList.of(page);
    }

    /**
     * 根据查询条件查询多条数据。
     *
     * @param whereConditions 条件
     * @return 数据列表
     */
    @Override
    default List<T> selectListByCondition(@NonNull QueryCondition whereConditions) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListByCondition(whereConditions);
        }
        return pageSelect(whereConditions, pageDomain);
    }

    /**
     * 根据查询条件查询数据列表。
     *
     * @param queryWrapper 条件
     * @return 数据列表
     * @see EntitySqlProvider#selectListByQuery(Map, ProviderContext)
     */
    @Override
    @Nullable
    default List<T> selectListByQuery(@NonNull QueryWrapper queryWrapper) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        Page<T> page;
        if (pageDomain == null) {
            page = Page.of(1, 10000000);
        } else {
            page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        }
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, null, false);
        return PageList.of(page);
    }

    /**
     * 根据查询条件查询数据列表。
     *
     * @param queryWrapper 条件
     * @param consumers    字段查询
     * @return 数据列表
     */
    @Override
    default @NonNull List<T> selectListByQuery(@NonNull QueryWrapper queryWrapper, Consumer<FieldQueryBuilder<T>>... consumers) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListByQuery(queryWrapper, consumers);
        }
        return pageSelect(queryWrapper, pageDomain, consumers);
    }

    /**
     * 根据查询条件查询 Row 数据。
     * <p>
     * 无分页
     *
     * @param queryWrapper 条件
     * @return 行数据
     */
    @Override
    @Nullable
    List<Row> selectRowsByQuery(@NonNull QueryWrapper queryWrapper);

    /**
     * 根据查询条件查询数据列表，要求返回的数据为 asType。这种场景一般用在 left join 时，
     * 有多出了实体类本身的字段内容，可以转换为 dto、vo 等场景。
     *
     * @param queryWrapper 条件
     * @param asType       接收数据类型
     * @return 数据列表
     */
    @Override
    default @Nullable <R> List<R> selectListByQueryAs(@NonNull QueryWrapper queryWrapper, @NonNull Class<R> asType) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListByQueryAs(queryWrapper, asType);
        }
        return pageSelect(queryWrapper, pageDomain, asType);
    }

    /**
     * 根据查询条件查询数据列表，要求返回的数据为 asType 类型。
     *
     * @param queryWrapper 条件
     * @param asType       接收的数据类型
     * @param consumers    字段查询
     * @return 数据列表
     */
    @Override
    default @NonNull <R> List<R> selectListByQueryAs(@NonNull QueryWrapper queryWrapper, @NonNull Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        // TODO 无法分页
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListByQueryAs(queryWrapper, asType, consumers);
        }
        throw new BaseException("暂不支持分页");
    }

    /**
     * 查询实体类及其 Relation 注解字段。
     *
     * @param queryWrapper 条件
     */
    @Override
    default @Nullable List<T> selectListWithRelationsByQuery(@NonNull QueryWrapper queryWrapper) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListWithRelationsByQuery(queryWrapper);
        }
        return pageSelect(queryWrapper, pageDomain);
    }

    /**
     * 查询实体类及其 Relation 注解字段。
     *
     * @param queryWrapper 条件
     * @param asType       要求返回的数据类型
     * @return 数据列表
     */
    @Override
    default @Nullable <R> List<R> selectListWithRelationsByQueryAs(@NonNull QueryWrapper queryWrapper, @NonNull Class<R> asType) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListWithRelationsByQueryAs(queryWrapper, asType);
        }
        return pageSelect(queryWrapper, pageDomain, asType);
    }

    /**
     * 查询实体类及其 Relation 注解字段。
     *
     * @param queryWrapper 条件
     * @param asType       返回的类型
     * @param consumers    字段查询
     * @return 数据列表
     */
    @Override
    default @NonNull <R> List<R> selectListWithRelationsByQueryAs(@NonNull QueryWrapper queryWrapper, @NonNull Class<R> asType, Consumer<FieldQueryBuilder<R>>... consumers) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectListWithRelationsByQueryAs(queryWrapper, asType, consumers);
        }
//        return pageSelect(queryWrapper, pageDomain, asType, consumers);
        throw new BaseException("暂不支持分页");
    }

    /**
     * 查询第一列返回的数据集合，QueryWrapper 执行的结果应该只有 1 列，例如：<br>
     * {@code QueryWrapper.create().select(ACCOUNT.id).where(...);}
     *
     * @param queryWrapper 查询包装器
     * @return 数据列表
     * @see EntitySqlProvider#selectObjectByQuery(Map, ProviderContext)
     */
    @Override
    @Nullable
    default List<Object> selectObjectListByQuery(@NonNull QueryWrapper queryWrapper) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        Page<Object> page;
        if (pageDomain == null) {
            page = Page.of(1, 10000000);
        } else {
            page = Page.of(pageDomain.getPageNum(), pageDomain.getPageSize());
        }
        page = MyMapperUtil.doPaginate(this, page, queryWrapper, null, false);
        return PageList.of(page);
    }

    /**
     * 查询第一列返回的数据集合，QueryWrapper 执行的结果应该只有 1 列，例如：<br>
     * {@code QueryWrapper.create().select(ACCOUNT.id).where(...);}
     *
     * @param queryWrapper 查询包装器
     * @param asType       转换成的数据类型
     * @return 数据列表
     */
    @Override
    default @NonNull <R> List<R> selectObjectListByQueryAs(@NonNull QueryWrapper queryWrapper, @NonNull Class<R> asType) {
        PageDomain pageDomain = PageUtils.getPageDomain();
        if (pageDomain == null) {
            return BaseMapper.super.selectObjectListByQueryAs(queryWrapper, asType);
        }
        return pageSelect(queryWrapper, pageDomain, asType);
    }

}
