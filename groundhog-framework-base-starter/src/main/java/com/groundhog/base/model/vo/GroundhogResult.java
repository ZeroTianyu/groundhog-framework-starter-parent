package com.groundhog.base.model.vo;

import lombok.Data;

/**
 * 通用返回对象
 *
 * @author guoty
 * @date 2021-08-29
 */
@Data
public class GroundhogResult<T> {
    private int code;
    private String message;
    private boolean success;
    private T data;

    public GroundhogResult() {
    }

    protected GroundhogResult(int code, String message, boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;

    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> GroundhogResult<T> success(T data) {
        return new GroundhogResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getSuccess(), data);
    }

    /**
     * 成功返回结果
     */
    public static <T> GroundhogResult<T> success() {
        return new GroundhogResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), ResultCode.SUCCESS.getSuccess(), null);
    }

    /**
     * 设置返回结果
     */
    public <T> GroundhogResult<T> setResult(ResultCode resultCode) {
        return new GroundhogResult<T>(resultCode.getCode(), resultCode.getMessage(),resultCode.getSuccess(), null);
    }


    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> GroundhogResult<T> success(T data, String message) {
        return new GroundhogResult<T>(ResultCode.SUCCESS.getCode(), message, ResultCode.SUCCESS.getSuccess(), data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     */
    public static <T> GroundhogResult<T> failed(IErrorCode errorCode) {
        return new GroundhogResult<T>(errorCode.getCode(), errorCode.getMessage(), errorCode.getSuccess(), null);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    public static <T> GroundhogResult<T> failed(IErrorCode errorCode, String message) {
        return new GroundhogResult<T>(errorCode.getCode(), message, errorCode.getSuccess(), null);
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public static <T> GroundhogResult<T> failed(int code, String message) {
        return new GroundhogResult<T>(code, message, false, null);
    }

    /**
     * 失败返回结果
     *
     * @param code    错误码
     * @param message 错误信息
     */
    public static <T> GroundhogResult<T> failed(int code, String message,boolean success) {
        return new GroundhogResult<T>(code, message, false, null);
    }


    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> GroundhogResult<T> failed(String message) {
        return new GroundhogResult<T>(ResultCode.FAILED.getCode(), message, ResultCode.FAILED.getSuccess(), null);
    }

    /**
     * 失败返回结果
     */
    public static <T> GroundhogResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> GroundhogResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> GroundhogResult<T> validateFailed(String message) {
        return new GroundhogResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, ResultCode.VALIDATE_FAILED.getSuccess(), null);
    }

    /**
     * 未登录返回结果
     */
    public static <T> GroundhogResult<T> unauthorized(T data) {
        return new GroundhogResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), ResultCode.UNAUTHORIZED.getSuccess(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> GroundhogResult<T> forbidden(T data) {
        return new GroundhogResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), ResultCode.FORBIDDEN.getSuccess(), data);
    }
}
