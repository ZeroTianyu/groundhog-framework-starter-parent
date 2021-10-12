package com.groundhog.base.excption;

import com.groundhog.base.model.vo.ResultCode;

import java.text.MessageFormat;

/**
 * @Description: 公共异常类
 * @Author Created by yan.x on 2019-07-27 .
 **/
public class GroundhogException extends AbstractGroundhogException {

    private Integer code = 5055;
    private String message;

    public GroundhogException() {
        super();
    }

    public GroundhogException(Throwable cause) {
        super(cause);
    }

    public GroundhogException(String message) {
        super(message);
        super.setCode(code);
        this.message = message;
    }


    public GroundhogException(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        super.setCode(code);
        super.setMessage(message);
    }

    public GroundhogException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getCode(), resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public GroundhogException(String message, Throwable cause) {
        super(cause, message);
        super.setCode(code);
        this.message = message;
    }

    public GroundhogException(Integer code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public GroundhogException(Integer code, String message, Throwable cause) {
        super(code, message, cause);
        this.code = code;
        this.message = message;
    }

    public GroundhogException(String pattern, Object... params) {
        super(pattern, params);
        super.setCode(code);
        this.message = MessageFormat.format(pattern, params);
    }

    public GroundhogException(Throwable cause, String pattern, Object... params) {
        super(pattern, params, cause);
        super.setCode(code);
        this.message = MessageFormat.format(pattern, params);
    }
}