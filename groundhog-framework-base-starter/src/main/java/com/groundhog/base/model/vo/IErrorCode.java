package com.groundhog.base.model.vo;

/**
 * 封装API的错误码
 *
 * @author guoty
 * @date 2021-08-29
 */
public interface IErrorCode {
    /**
     * 获取错误码
     *
     * @return
     */
    int getCode();

    /**
     * 获取错误信息
     *
     * @return
     */
    String getMessage();


    /**
     * 获取状态
     * @return
     */
    boolean getSuccess();
}
