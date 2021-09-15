package com.groundhog.mybatis.plus.utils;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.groundhog.base.model.vo.GroundhogPageable;
import com.groundhog.base.model.vo.PageModel;
import com.groundhog.mybatis.plus.constant.FieldsPool;
import com.groundhog.utils.string.DggStringUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @Description: 查询条件包装工具集
 * @Author Created by HOT SUN on 2020/9/28 .
 **/
public final class Wraps {

    private Wraps() {
        // ignore
    }

    public static <T> LambdaQueryWrapper<T> eq(SFunction<T, ?> column, Object value) {
        LambdaQueryWrapper<T> wrapper = Wraps.lbQ();
        wrapper.eq(column, value);
        return wrapper;
    }

    public static <T> LambdaQueryWrapper<T> in(SFunction<T, ?> column, Object... values) {
        LambdaQueryWrapper<T> wrapper = Wraps.lbQ();
        wrapper.in(column, values);
        return wrapper;
    }

    /**
     * 获取 QueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> QueryWrapper<T> q() {
        return Wrappers.query();
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> lbQ() {
        return Wrappers.lambdaQuery();
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> lbQ(T entity) {
        return Wrappers.lambdaQuery(entity);
    }

    /**
     * 获取 LambdaQueryWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaQueryWrapper&lt;T&gt;
     */
    public static <T> LambdaQueryWrapper<T> lbQ(Class<T> entityClass) {
        return Wrappers.lambdaQuery(entityClass);
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> lbU() {
        return Wrappers.lambdaUpdate();
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> lbU(T entity) {
        return Wrappers.lambdaUpdate(entity);
    }

    /**
     * 获取 LambdaUpdateWrapper&lt;T&gt;
     *
     * @param <T> 实体类泛型
     * @return LambdaUpdateWrapper&lt;T&gt;
     */
    public static <T> LambdaUpdateWrapper<T> lbU(Class<T> entityClass) {
        return Wrappers.lambdaUpdate(entityClass);
    }

    /**
     * LambdaQueryWrapper 查询字段
     *
     * @param selectColumns
     * @param <T>
     * @return
     */
    public static <T> LambdaQueryWrapper<T> select(SFunction<T, ?>... selectColumns) {
        LambdaQueryWrapper<T> wrapper = Wraps.lbQ();
        wrapper.select(selectColumns);
        return wrapper;
    }

    /***
     * 分页查询
     * @param params  查询条件，主要是分页的（页码、显示内容、排序规则）
     * @return
     */
    public static <T> Page<T> page(GroundhogPageable pageable) {
        return new Page<T>(pageable.getCurrentPage(), pageable.getLimit());
    }

    /***
     * 分页查询
     * @param params  查询条件，主要是分页的（页码、显示内容、排序规则）
     * @param orderByFields 排序规则
     * @param isAsc   升序 / 降序
     * @return
     */
    public static <T> IPage<T> page(GroundhogPageable pageable, String orderByFields, Boolean isAsc) {
        return Wraps.page(pageable, orderByFields, isAsc, null);
    }

    /***
     * 分页查询
     * @param params            : 查询条件，主要是分页的（页码、显示内容、排序规则）
     * @param orderByFields     : 排序规则
     * @param isAsc             : 升序 / 降序
     * @param defaultOrderField : 默认排序字段
     * @return
     */
    public static <T> IPage<T> page(GroundhogPageable pageable, String orderByFields, Boolean isAsc, String defaultOrderField) {
        Page<T> page = Wraps.page(pageable);
        List<OrderItem> orderItems = Wraps.orderBy(orderByFields, isAsc, defaultOrderField);
        if (null != orderItems) {
            page.addOrder(orderItems);
        }
        return page;
    }

    /**
     * LambdaQueryWrapper 排序
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> LambdaQueryWrapper orderBy(final PageModel model) {
        QueryWrapper<T> wrapper = Wraps.q();
        String orderBy = model.getOrderBy();
        Boolean isAsc = model.getIsAsc();
        if (StringUtils.isNotBlank(orderBy)) {
            String[] split = Arrays.stream(orderBy.split("[,;]")).map(DggStringUtil::camelToUnderline).toArray(String[]::new);
            wrapper.orderBy(true, null == isAsc ? false : isAsc, Arrays.asList(split));
            return wrapper.lambda();
        }
        wrapper.orderBy(true, null == isAsc ? false : isAsc, FieldsPool.CREATE_TIME_COLUMN);
        return wrapper.lambda();
    }

    /**
     * 排序规则
     *
     * @param orderBy 排序规则
     * @param isAsc   升序 / 降序
     * @return
     */
    public static List<OrderItem> orderBy(String orderByFields, Boolean isAsc, String defaultOrderField) {
        String[] fields = Wraps.toArray(orderByFields, defaultOrderField);
        if (null == fields) {
            return null;
        }
        isAsc = null == isAsc ? false : isAsc;
        return isAsc ? OrderItem.ascs(fields) : OrderItem.descs(fields);
    }

    private static String[] toArray(String str, String defaultStr) {
        if (StringUtils.isNotBlank(str)) {
            return Arrays.stream(str.split("[,;]")).map(DggStringUtil::camelToUnderline).toArray(String[]::new);
        }
        if (StringUtils.isNotBlank(defaultStr)) {
            return new String[]{defaultStr};
        }
        return null;
    }
}