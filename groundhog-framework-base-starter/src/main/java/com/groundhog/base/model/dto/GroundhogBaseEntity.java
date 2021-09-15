package com.groundhog.base.model.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: guotianyu
 * @description:
 * @create: 2021/09/14 16:16
 */
@Data
public class GroundhogBaseEntity implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 创建人ID
     */
    private Long createUserId;

    /**
     * 创建人姓名
     */
    private String createUserName;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


    /**
     * 创建人ID
     */
    private Long updateUserId;

    /**
     * 创建人姓名
     */
    private String updateUserName;
}
