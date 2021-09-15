package com.groundhog.mybatis.plus.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.groundhog.base.model.vo.GroundhogPageable;
import com.groundhog.base.model.vo.PageModel;
import com.groundhog.mybatis.plus.func.SFunction;
import com.groundhog.mybatis.plus.utils.Wraps;

import java.util.List;

/**
 * @Description: Service基类, 提供MP方法扩展
 * @Author Created by yan.x on 2020-10-14 .
 **/
public interface SuperService<T> extends IService<T> {

    /**
     * 翻页查询
     *
     * @param pageable
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @return
     */
    default GroundhogPageable<T> page(GroundhogPageable<T> pageable, Wrapper<T> queryWrapper) {
        Page<T> page = getBaseMapper().selectPage(Wraps.page(pageable), queryWrapper);
        pageable.setResult(page.getTotal(), page.getRecords());
        return pageable;
    }

    /**
     * 翻页查询
     *
     * @param pageModel
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @param mapper
     * @param <E>
     * @return
     */
    default <E> GroundhogPageable<E> page(PageModel pageModel, Wrapper<T> queryWrapper, SFunction<T, E> mapper) {
        return this.page(GroundhogPageable.build(pageModel), queryWrapper, mapper);
    }

    /**
     * 翻页查询
     *
     * @param pageable
     * @param queryWrapper 实体对象封装操作类 {@link com.baomidou.mybatisplus.core.conditions.query.QueryWrapper}
     * @param mapper
     * @param <E>
     * @return
     */
    default <E> GroundhogPageable<E> page(GroundhogPageable<E> pageable, Wrapper<T> queryWrapper, SFunction<T, E> mapper) {
        Page<T> page = getBaseMapper().selectPage(Wraps.page(pageable), queryWrapper);
        List<E> records = mapper.apply(page.getRecords());
        pageable.setResult(page.getTotal(), records);
        return pageable;
    }

    /**
     * 修改状态
     *
     * @param id
     * @param status
     * @return
     */
    boolean updateStatus(Long id, Integer status);

    /**
     * 是否存在
     *
     * @param wrapper
     * @return
     */
    default boolean exist(LambdaQueryWrapper<T> wrapper) {
        return SqlHelper.retBool(getBaseMapper().selectCount(wrapper).intValue());
    }
}
