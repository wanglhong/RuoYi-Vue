package com.ruoyi.common.utils;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.field.FieldQueryBuilder;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.util.MapperUtil;
import com.ruoyi.common.core.mapper.MyBaseMapper;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class MyMapperUtil {

    /**
     * <p>原生的、未经过优化的 COUNT 查询。抛开效率问题不谈，只关注结果的准确性，
     * 这个 COUNT 查询查出来的分页总数据是 100% 正确的，不接受任何反驳。
     *
     * <p>为什么这么说，因为是用子查询实现的，生成的 SQL 如下：
     *
     * <p><pre>
     * {@code
     * SELECT COUNT(*) AS `total` FROM ( ...用户构建的 SQL 语句... ) AS `t`;
     * }
     * </pre>
     *
     * <p>不进行 SQL 优化的时候，返回的就是这样的 COUNT 查询语句。
     */
    public static @NonNull QueryWrapper rawCountQueryWrapper(@NonNull QueryWrapper queryWrapper) {
        return MapperUtil.rawCountQueryWrapper(queryWrapper);
    }

    public static @NonNull QueryWrapper rawCountQueryWrapper(@NonNull QueryWrapper queryWrapper, @Nullable List<QueryColumn> customCountColumns) {
        return MapperUtil.rawCountQueryWrapper(queryWrapper, customCountColumns);
    }

    /**
     * 优化 COUNT 查询语句。
     */
    public static @NonNull QueryWrapper optimizeCountQueryWrapper(@NonNull QueryWrapper queryWrapper) {
        return MapperUtil.optimizeCountQueryWrapper(queryWrapper);
    }

    /**
     * 优化 COUNT 查询语句。
     */
    public static @NonNull QueryWrapper optimizeCountQueryWrapper(@NonNull QueryWrapper queryWrapper, @Nullable List<QueryColumn> customCountColumns) {
        return MapperUtil.optimizeCountQueryWrapper(queryWrapper, customCountColumns);
    }

    public static boolean hasDistinct(List<QueryColumn> selectColumns) {
        return MapperUtil.hasDistinct(selectColumns);
    }

    @SafeVarargs
    public static <T, R> @NonNull Page<R> doPaginate(@NonNull BaseMapper<T> mapper, @NonNull Page<R> page, @NonNull QueryWrapper queryWrapper, @Nullable Class<R> asType, boolean withRelations, Consumer<FieldQueryBuilder<R>>... consumers) {
        return MapperUtil.doPaginate(mapper, page, queryWrapper, asType, withRelations, consumers);
    }

    public static <R> void queryFields(@NonNull BaseMapper<?> mapper, @Nullable List<R> list, @Nullable Consumer<FieldQueryBuilder<R>>[] consumers) {
        MapperUtil.queryFields(mapper, list, consumers);
    }

    public static <E> @Nullable E queryRelations(@NonNull BaseMapper<?> mapper, @Nullable E entity) {
        return MapperUtil.queryRelations(mapper, entity);
    }

    public static <E> @NonNull List<E> queryRelations(@NonNull BaseMapper<?> mapper, @NonNull List<E> entities) {
        return MapperUtil.queryRelations(mapper, entities);
    }

    public static @NonNull Class<? extends Collection> getCollectionWrapType(@NonNull Class<?> type) {
        return MapperUtil.getCollectionWrapType(type);
    }

    /**
     * 搬运加改造 {@link com.mybatisflex.core.util.MapperUtil#getSelectOneResult}
     */
    public static <T> @Nullable T getSelectOneResult(@Nullable List<T> list) {
        return MapperUtil.getSelectOneResult(list);
    }

    public static long getLongNumber(List<Object> objects) {
        return MapperUtil.getLongNumber(objects);
    }

    public static @NonNull Map<String, Object> preparedParams(@NonNull BaseMapper<?> baseMapper, @NonNull Page<?> page, @Nullable QueryWrapper queryWrapper, @Nullable Map<String, Object> params) {
        return MapperUtil.preparedParams(baseMapper, page, queryWrapper, params);
    }

}
