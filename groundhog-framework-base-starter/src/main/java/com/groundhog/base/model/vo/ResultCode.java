package com.groundhog.base.model.vo;

/**
 * 枚举了一些常用API操作码
 *
 * @author guoty
 * @date 2021-08-29
 */
public enum ResultCode implements IErrorCode {
    SUCCESS(200, "操作成功", true),
    FAILED(500, "操作失败", false),


    /**
     * 400 --- 499 账号类异常
     */
    UNAUTHORIZED(401, "暂未登录或token已经过期", false),
    USER_NOT_EXIST(402, "用户不存在，请先绑定手机号等操作", false),
    FORBIDDEN(403, "没有相关权限", false),
    VALIDATE_FAILED(405, "参数检验失败", false),
    USER_EXISTS(410, "用户已存在", false),

    /**
     * 600-699 业务错误
     */
    ROLE_NOT_EXIST(601, "权限code不存在", false),
    USER_ROLE_EXIST(602, "用户已经存在该权限", false),
    USER_FOLLOWED(603, "该用户您已经关注", false),
    WE_CHAT_TOKEN_ERROR(605, "access_token无效", false),
    RESOURCES_NOT_EXIST(606, "资源不存在", false),
    REPEAT_OPERATION(607, "请勿重复操作", false),
    COMMENT_NOT_TOP(608, "父级评论不是1级评论", false),
    TOO_MANY_IMAGES(609, "图片过多", false),


    /**
     * 700-799 服务器内部
     */
    REMOTE_CALL(700, "服务内部出错", false),


    ;


    private Integer code;
    private String message;
    private boolean success;

    ResultCode(Integer code, String message, boolean success) {
        this.code = code;
        this.message = message;
        this.success = success;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }
}
