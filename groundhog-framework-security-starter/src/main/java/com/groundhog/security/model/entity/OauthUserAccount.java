package com.groundhog.security.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.groundhog.base.model.dto.GroundhogBaseEntity;
import lombok.Data;

/**
 * @author: guotianyu
 * @description:
 * @create: 2021/08/27 16:17
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OauthUserAccount extends GroundhogBaseEntity {
    /**
     * id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 密码（BCrypt）
     */
    private String password;

    /**
     * 状态(0禁用，1启用)
     */
    private Boolean status;



}
