package com.groundhog.base.excption;

import com.groundhog.base.model.vo.ResultCode;

import java.text.MessageFormat;

/**
 * @author guotianyu
 */
public abstract class AbstractGroundhogException extends RuntimeException {
    private static final long serialVersionUID = 8247610319171014183L;

    private int code = 5000;
    private String message = "服务异常,请稍后再试!";

    public AbstractGroundhogException(ResultCode resultCode, Throwable cause) {
        super(resultCode.getMessage(), cause);
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    public AbstractGroundhogException(int code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
        this.message = message;
    }

    public AbstractGroundhogException(int code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AbstractGroundhogException(Throwable cause) {
        super(cause);
    }

    public AbstractGroundhogException(String message) {
        super(message);
        this.message = message;
    }

    public AbstractGroundhogException() {
        super("crisps framework base异常");
    }

    public AbstractGroundhogException(Throwable cause, String pattern, Object... params) {
        super(MessageFormat.format(pattern, params), cause);
        this.message = MessageFormat.format(pattern, params);
    }

    public AbstractGroundhogException(String pattern, Object... params) {
        super(MessageFormat.format(pattern, params));
        this.message = MessageFormat.format(pattern, params);
    }

    public Integer getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}