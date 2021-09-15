package com.groundhog.base.constant;

/**
 * @Description: 返回状态池
 * @Author Created by yan.x on 2019-04-02 .
 * @Copyright © HOT SUN group.All Rights Reserved.
 **/
public interface ResultPool {

    /**
     * 请求成功码
     */
    public static final int REQUEST_SUCCESS_CODE = 200;

    /**
     * 失败异常错误码
     */
    public static final int UNKNOW_ERROR_CODE_1 = 1;

    /**
     * 验证异常
     */
    public static final int VALIDATE_ERROR_CODE = 5001;

    /**
     * 资源不存在
     */
    public static final int NOT_FOUND_ERROR_CODE = 5002;

    /**
     * 访问失败
     */
    public static final int INVOKE_FAILURE_ERROR_CODE = 5003;

    /**
     * 访问受限
     */
    public static final int ACCESS_DENIED_ERROR_CODE = 5005;

    /**
     * 请求参数有误
     */
    public static final int PARAMETER_ERROR_CODE = 5006;

    /**
     * 调用异常错误码
     */
    public static final int FEGIN_ERROR_CODE = 5054;

    /**
     * 系统异常错误码
     */
    public static final int UNKNOW_ERROR_CODE = 5055;

    /**
     * 用户异常错误码(登录失败/无效用户信息)
     */
    public static final int USER_ERROR_CODE = 5223;

    /**
     * HTTP请求错误码
     */
    public static final int HTTP_REQUEST_ERROR_CODE = 5600;
}