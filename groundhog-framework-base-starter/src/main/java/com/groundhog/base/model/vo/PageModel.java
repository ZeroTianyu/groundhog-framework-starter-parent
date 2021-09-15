package com.groundhog.base.model.vo;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Description 分页基类
 * @Version 1.0.0-SNAPSHOT
 * @Author Created by hot.sun on 2019/9/7 .
 **/
@Data
public abstract class PageModel<T> {
    @NotNull(message = "start 不可为null")
    @Min(value = 0, message = "start 不可小于0")
    @Max(value = Integer.MAX_VALUE, message = "start 最大值超限")
    private Integer start = 1;
    @NotNull(message = "limit 不可为null")
    @Min(value = 1, message = "limit 不可小于1")
    @Max(value = Integer.MAX_VALUE, message = "limit 最大值超限")
    private Integer limit = 10;
    private String orderBy;
    private Boolean isAsc;
}
