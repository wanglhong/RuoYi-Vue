package com.ruoyi.common.core.service;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.utils.PageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * BaseService 实现类（兼容 MyBatis-Flex 分页）
 *
 * @author ruoyi
 */
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> {

}
