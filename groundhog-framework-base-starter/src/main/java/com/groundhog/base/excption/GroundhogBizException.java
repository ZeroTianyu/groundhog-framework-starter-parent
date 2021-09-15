package com.groundhog.base.excption;

import com.groundhog.base.model.vo.ResultCode;

import java.text.MessageFormat;

/**
 * @Description: 公共异常类
 * @Author Created by yan.x on 2019-07-27 .
 **/
public class GroundhogBizException extends AbstractCrispsException {

    private int code = 500;
    private String message;

    public GroundhogBizException() {
        super();
    }

    public GroundhogBizException(Throwable cause) {
        super(cause);
    }

    public GroundhogBizException(String message) {
        super(message);
        super.setCode(code);
        this.message = message;
    }


    public GroundhogBizException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        super.setCode(code);
        super.setMessage(message);
    }

    public GroundhogBizException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getCode(), resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public GroundhogBizException(String message, Throwable cause) {
        super(cause, message);
        super.setCode(code);
        this.message = message;
    }

    public GroundhogBizException(int code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public GroundhogBizException(int code, String message, Throwable cause) {
        super(code, message, cause);
        this.code = code;
        this.message = message;
    }

    public GroundhogBizException(String pattern, Object... params) {
        super(pattern, params);
        super.setCode(code);
        this.message = MessageFormat.format(pattern, params);
    }

    public GroundhogBizException(Throwable cause, String pattern, Object... params) {
        super(pattern, params, cause);
        super.setCode(code);
        this.message = MessageFormat.format(pattern, params);
    }
}
